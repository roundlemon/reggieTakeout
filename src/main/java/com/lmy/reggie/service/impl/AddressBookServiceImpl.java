package com.lmy.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmy.reggie.entity.AddressBook;
import com.lmy.reggie.entity.User;
import com.lmy.reggie.mapper.AddressBookMapper;
import com.lmy.reggie.mapper.UserMapper;
import com.lmy.reggie.service.AddressBookService;
import com.lmy.reggie.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
