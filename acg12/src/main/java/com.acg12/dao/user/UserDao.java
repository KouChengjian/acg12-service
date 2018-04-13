package com.acg12.dao.user;


import com.acg12.entity.po.user.User;

import java.util.List;

/**
 * Created by kouchengjian on 2017/3/2.
 */
public interface UserDao {

    int insert(User user);

    User queryUser(int id);

    User queryUserName(String username);

    int deleteUser(int id);

    int updateUser(User user);

    List<User> queryUserList();




}
