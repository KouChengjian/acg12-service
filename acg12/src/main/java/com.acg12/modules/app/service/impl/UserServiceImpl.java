package com.acg12.modules.app.service.impl;

import com.acg12.modules.app.dao.UserDao;
import com.acg12.modules.app.entity.po.user.User;
import com.acg12.modules.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kouchengjian on 2017/3/29.
 */
//@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userMapper;

    @Override
    public int saveUser(User u) throws Exception {
        return userMapper.insert(u);
    }

    @Override
    public User queryUser(int id) throws Exception {
        return userMapper.queryUser(id);
    }

    @Override
    public User queryUserName(String username) throws Exception {
        return userMapper.queryUserName(username);
    }

    @Override
    public int deleteUser(int id) throws Exception {
        return userMapper.deleteUser(id);
    }

    @Override
    public int updateUser(User user) throws Exception {
        return userMapper.updateUser(user);
    }

    @Override
    public List<User> queryUserList() throws Exception {
        return userMapper.queryUserList();
    }
}
