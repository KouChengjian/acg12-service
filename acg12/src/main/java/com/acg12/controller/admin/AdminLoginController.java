package com.acg12.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/9/11 16:05
 * Description: 登录页面
 */
@Controller("adminLoginController")
//@RequestMapping("admin/*")
public class AdminLoginController {

    /**
     * 登录
     * @return
     */
    @RequestMapping(name="/admin/login.html",method =RequestMethod.GET)
//    @RequestMapping(name="/login.html",method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        System.out.println("============");
        return "/login/index";
    }

    @ResponseBody
    @RequestMapping(name="/login.json",method = RequestMethod.POST)
    public String index(HttpServletRequest request) {
        System.out.println("============");
        return "ssssssss";
    }
}
