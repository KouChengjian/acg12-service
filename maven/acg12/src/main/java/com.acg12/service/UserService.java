package com.acg12.service;


import com.acg12.beans.User;

/**
 * Created by kouchengjian on 2017/3/9.
 */
public interface UserService {

    int saveUser(User u) throws Exception;

    User queryUser(int id) throws Exception;
}
