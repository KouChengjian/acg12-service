package com.acg12.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/9/11 16:05
 * Description: 登录页面
 */
@Controller("adminLoginController")
@RequestMapping("api/*")
public class AdminLoginController {

    /**
     * 登录
     * @return
     */
    @RequestMapping(name="/login",method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        System.out.println("============");
        return "/login/index";
    }
}
