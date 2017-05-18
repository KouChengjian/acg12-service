package com.acg12.service.impl;

import com.acg12.beans.User;
import com.acg12.mapper.UserMapper;
import com.acg12.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by kouchengjian on 2017/3/29.
 */
public class UserServiceImpl implements UserService {

//    @Autowired
    @Resource
    private UserMapper userMapper;

    @Override
    public void saveUser(User u) throws Exception {
        userMapper.insert(u);
    }


}
