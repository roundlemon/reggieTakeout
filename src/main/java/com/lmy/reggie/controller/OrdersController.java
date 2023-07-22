package com.lmy.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmy.reggie.common.BaseContext;
import com.lmy.reggie.common.R;
import com.lmy.reggie.dto.OrdersDto;
import com.lmy.reggie.entity.OrderDetail;
import com.lmy.reggie.entity.Orders;
import com.lmy.reggie.entity.ShoppingCart;
import com.lmy.reggie.entity.User;
import com.lmy.reggie.service.OrderDetailService;
import com.lmy.reggie.service.OrdersService;
import com.lmy.reggie.service.ShoppingCartService;
import com.lmy.reggie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders order){
        log.info("结算订单");
        ordersService.submit(order);
        return R.success("用户下单成功");
    }

    @GetMapping("/userPage")
    public R<Page> list(int page,int pageSize){
        Long currentId = BaseContext.getCurrentId();

        Page<Orders> pageInfo = new Page<>(page,pageSize);

        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUserId,currentId);

        Page<Orders> ordersPage = ordersService.page(pageInfo, queryWrapper);
        Page<OrdersDto> dtoPage = new Page<>();

        BeanUtils.copyProperties(ordersPage,dtoPage,"records");

        List<Orders> ordersList = pageInfo.getRecords();

        List<OrdersDto> ordersDtoList = ordersList.stream().map((item) -> {
            OrdersDto ordersDto = new OrdersDto();
            BeanUtils.copyProperties(item, ordersDto);
            Long orderId = item.getId();
            LambdaQueryWrapper<OrderDetail> orderDetailLambdaQueryWrapper = new LambdaQueryWrapper<>();
            orderDetailLambdaQueryWrapper.eq(OrderDetail::getOrderId, orderId);
            List<OrderDetail> orderDetailList = orderDetailService.list(orderDetailLambdaQueryWrapper);

            ordersDto.setOrderDetails(orderDetailList);
            return ordersDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(ordersDtoList);

        return R.success(dtoPage);
    }

    @PostMapping("/again")
    public R<String> again(@RequestBody Map<String,Long> map){
        System.out.println(map);

        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOrderId,map.get("id"));
        List<OrderDetail> orderDetailList = orderDetailService.list(queryWrapper);

        List<ShoppingCart> shoppingCartList = orderDetailList.stream().map((item) -> {
            ShoppingCart shoppingCart = new ShoppingCart();
            BeanUtils.copyProperties(item, shoppingCart);
            shoppingCart.setUserId(BaseContext.getCurrentId());
            shoppingCart.setCreateTime(LocalDateTime.now());
            return shoppingCart;
        }).collect(Collectors.toList());

        shoppingCartService.saveBatch(shoppingCartList);

        return R.success("再来一单成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,Long number,String beginTime, String endTime){
        Page<Orders> ordersPage = new Page<>(page,pageSize);

        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(number!=null,Orders::getId,number);
        queryWrapper.orderByDesc(Orders::getOrderTime);
        queryWrapper.gt(!StringUtils.isEmpty(beginTime),Orders::getOrderTime,beginTime)
                    .lt(!StringUtils.isEmpty(endTime),Orders::getOrderTime,endTime);



        Page<Orders> pageInfo = ordersService.page(ordersPage,queryWrapper);

        Page<OrdersDto> dtoPage = new Page<>();
        BeanUtils.copyProperties(pageInfo,dtoPage,"records");

        List<OrdersDto> collect = pageInfo.getRecords().stream().map((item) -> {
            OrdersDto ordersDto = new OrdersDto();

            BeanUtils.copyProperties(item, ordersDto);

            ordersDto.setUserName(ordersDto.getConsignee());
            return ordersDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(collect);
        return R.success(dtoPage);
    }

    @PutMapping
    public R<String> updateStatus(@RequestBody Orders order){
        Long orderId = order.getId();
        Orders orders = ordersService.getById(orderId);
        orders.setStatus(order.getStatus());

        ordersService.updateById(orders);
        return R.success("修改成功");
    }

}
