package com.acg12.web.view;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/11/2.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminView {

//    @ResponseBody
//    @RequestMapping(value = "/index" )
//    public ModelAndView index() {
//        return new ModelAndView("/client/index.html");
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/search" )
//    public ModelAndView search() {
//        return new ModelAndView("/client/search.html");
//    }

    @ResponseBody
    @RequestMapping(value = "/index1" )
    public String toExitPage() {
        return "/WEB-INF/jsp/index.jsp";
    }

    @RequestMapping(value="/index2" )
    public ModelAndView showData_1(){
        String message = "这个是要传递的数据";
        return new ModelAndView("/WEB-INF/jsp/index.jsp");
    }

}
