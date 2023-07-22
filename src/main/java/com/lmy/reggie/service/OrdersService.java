package com.lmy.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmy.reggie.entity.Orders;
import com.lmy.reggie.entity.User;

public interface OrdersService extends IService<Orders> {
    void submit(Orders orders);
}
