package com.lmy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmy.reggie.entity.ShoppingCart;
import com.lmy.reggie.entity.User;
import com.lmy.reggie.mapper.ShoppingCartMapper;
import com.lmy.reggie.mapper.UserMapper;
import com.lmy.reggie.service.ShoppingCartService;
import com.lmy.reggie.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
