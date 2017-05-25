package com.acg12.service;


import com.acg12.beans.User;

import java.util.List;

/**
 * Created by kouchengjian on 2017/3/9.
 */
public interface UserService {

    int saveUser(User u) throws Exception;

    User queryUser(int id) throws Exception;

    User queryUserName(String username) throws Exception;

    int deleteUser(int id) throws Exception;

    int updateUser(User user) throws Exception;

    List<User> queryUserList() throws Exception;
}
