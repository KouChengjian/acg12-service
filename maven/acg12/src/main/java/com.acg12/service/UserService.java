package com.acg12.service;


import com.acg12.beans.User;

/**
 * Created by kouchengjian on 2017/3/9.
 */
public interface UserService {

    void saveUser(User u) throws Exception;

    void queryUser(int id) throws Exception;
}
