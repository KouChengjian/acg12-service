package com.acg12.service.impl;



import com.acg12.beans.User;
import com.acg12.mapper.UserMapper;
import com.acg12.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;


public class ItemsServiceImpl implements ItemsService {

    //http://blog.csdn.net/xlxxybz1314/article/details/51404700



    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveUser(User u) throws Exception {
        userMapper.insert(u);
    }




}
