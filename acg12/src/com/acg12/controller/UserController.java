package com.acg12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kouchengjian on 2017/3/6.
 */
@Controller
@RequestMapping(value = "/u")
public class UserController {

    @RequestMapping(value = "/info" , method = {RequestMethod.POST, RequestMethod.GET})
    public String userInfo() {
        return "dfgh";
    }

}
