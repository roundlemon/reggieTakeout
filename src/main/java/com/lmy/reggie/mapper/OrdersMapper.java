package com.lmy.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmy.reggie.entity.Orders;
import com.lmy.reggie.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
