package com.lmy.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmy.reggie.dto.DishDto;
import com.lmy.reggie.entity.Category;
import com.lmy.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);
    public DishDto getByIdWithFlavor(Long id);
    public void updateWithFlavor(DishDto dishDto);
}
