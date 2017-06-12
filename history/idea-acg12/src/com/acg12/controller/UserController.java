package com.acg12.controller;

import com.acg12.beans.User;
import com.acg12.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by kouchengjian on 2017/3/6.
 */
@Controller
@RequestMapping(value = "/u")
public class UserController {

    //@Resource
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/sava" , method = {RequestMethod.POST, RequestMethod.GET})
    public String userInfo() {
        User u = new User();
//        u.setId(1);
        u.setUsername("1111");
        u.setPassword("123456");
        try {
            userService.saveUser(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u.getId()+"===";
    }

//    @ResponseBody
//    @RequestMapping(value="/get/{userNo}", method=RequestMethod.GET)
//    public String get(@PathVariable String userNo, Model model){
//        String username = userService.get(userNo);
//        return username;
//    }

}
