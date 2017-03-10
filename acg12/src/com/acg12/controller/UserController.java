package com.acg12.controller;

import com.acg12.service.UserService;
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

    @Resource
    private UserService userService;

    @RequestMapping(value = "/info" , method = {RequestMethod.POST, RequestMethod.GET})
    public String userInfo() {
        return "dfgh";
    }

    @ResponseBody
    @RequestMapping(value="/get/{userNo}", method=RequestMethod.GET)
    public String get(@PathVariable String userNo, Model model){
        String username = userService.get(userNo);
        return username;
    }

}
