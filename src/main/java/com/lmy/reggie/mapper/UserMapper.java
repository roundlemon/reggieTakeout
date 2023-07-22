package com.lmy.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmy.reggie.entity.Dish;
import com.lmy.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
