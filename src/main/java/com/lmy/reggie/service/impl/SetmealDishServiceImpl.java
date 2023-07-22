package com.lmy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmy.reggie.entity.Setmeal;
import com.lmy.reggie.entity.SetmealDish;
import com.lmy.reggie.mapper.SetmealDishMapper;
import com.lmy.reggie.mapper.SetmealMapper;
import com.lmy.reggie.service.SetmealDishService;
import com.lmy.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {
}
