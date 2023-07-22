package com.lmy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmy.reggie.entity.Employee;
import com.lmy.reggie.entity.User;
import com.lmy.reggie.mapper.EmployeeMapper;
import com.lmy.reggie.mapper.UserMapper;
import com.lmy.reggie.service.EmployeeService;
import com.lmy.reggie.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
