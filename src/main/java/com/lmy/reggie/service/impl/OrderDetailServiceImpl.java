package com.lmy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmy.reggie.entity.OrderDetail;
import com.lmy.reggie.entity.Orders;
import com.lmy.reggie.mapper.OrderDetailMapper;
import com.lmy.reggie.mapper.OrdersMapper;
import com.lmy.reggie.service.OrderDetailService;
import com.lmy.reggie.service.OrdersService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
