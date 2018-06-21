package com.acg12.modules.app.dao;

import com.acg12.modules.app.entity.po.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by kouchengjian on 2017/3/2.
 */
@Mapper
public interface UserDao {

    int insert(User user);

    User queryUser(int id);

    User queryUserName(String username);

    int deleteUser(int id);

    int updateUser(User user);

    List<User> queryUserList();




}
