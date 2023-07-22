package com.lmy.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lmy.reggie.dto.SetmealDto;
import com.lmy.reggie.entity.Dish;
import com.lmy.reggie.entity.Setmeal;

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);
    public SetmealDto getByIdWithDish(Long id);
    public void updateWithDish(SetmealDto setmealDto);
}
