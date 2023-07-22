package com.lmy.reggie.dto;


import com.lmy.reggie.entity.Setmeal;
import com.lmy.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
