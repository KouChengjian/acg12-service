package com.acg12.dao;


import com.acg12.entity.po.User;

import java.util.List;

/**
 * Created by kouchengjian on 2017/3/2.
 */
public interface UserMapper {

    int insert(User user);

    User queryUser(int id);

    User queryUserName(String username);

    int deleteUser(int id);

    int updateUser(User user);

    List<User> queryUserList();




}
