package com.acg12.web.admin.ui;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/11/2.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminView {

    @RequestMapping(value = "/index1")
    public String toExitPage(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

}
