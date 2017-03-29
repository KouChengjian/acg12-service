package com.acg12.service;



import com.acg12.beans.User;
import org.springframework.stereotype.Service;

/**
 * Created by kouchengjian on 2017/3/9.
 */
//@Service
public interface UserService {

    void saveUser(User u) throws Exception;
}
