package com.lmy.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmy.reggie.common.R;
import com.lmy.reggie.dto.DishDto;
import com.lmy.reggie.entity.Category;
import com.lmy.reggie.entity.Dish;
import com.lmy.reggie.entity.DishFlavor;
import com.lmy.reggie.mapper.CategoryMapper;
import com.lmy.reggie.mapper.DishMapper;
import com.lmy.reggie.service.CategoryService;
import com.lmy.reggie.service.DishFlavorService;
import com.lmy.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        this.save(dishDto);

        Long dishId = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        for (int i = 0; i < flavors.size(); i++) {
            flavors.get(i).setDishId(dishId);
        }
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public DishDto getByIdWithFlavor(Long id) {
        Dish dish = this.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);

        LambdaQueryWrapper<DishFlavor> flavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        flavorLambdaQueryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> dishFlavorList = dishFlavorService.list(flavorLambdaQueryWrapper);
        dishDto.setFlavors(dishFlavorList);

        return dishDto;

    }

    @Override
    public void updateWithFlavor(DishDto dishDto) {
        this.updateById(dishDto);
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);

        List<DishFlavor> dishFlavorList= dishDto.getFlavors();
        for (DishFlavor dishFlavor : dishFlavorList) {
            dishFlavor.setDishId(dishDto.getId());
        }

        dishFlavorService.saveBatch(dishFlavorList);

    }
}
