package com.acg12.mapper;


import com.acg12.beans.User;

import java.util.List;

/**
 * Created by kouchengjian on 2017/3/2.
 */
public interface UserMapper {

    int insert(User user);

    User queryUser(int id);

    int deleteUser(int id);

    int updateUser(User user);

    List<User> queryUserList();




}
