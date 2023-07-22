package com.lmy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmy.reggie.entity.Dish;
import com.lmy.reggie.entity.DishFlavor;
import com.lmy.reggie.mapper.DishFlavorMapper;
import com.lmy.reggie.mapper.DishMapper;
import com.lmy.reggie.service.DishFlavorService;
import com.lmy.reggie.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
