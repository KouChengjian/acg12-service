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

    @Resource
    private UserMapper userMapper;

    @Override
    public int saveUser(User u) throws Exception {
        return userMapper.insert(u);
    }

    @Override
    public User queryUser(int id) throws Exception {
        return userMapper.queryUser(id);
    }


}
