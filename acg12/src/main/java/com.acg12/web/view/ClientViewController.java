package com.acg12.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2018/3/21.
 */
@Controller
@RequestMapping(value = "/view" )
public class ClientViewController {

    @ResponseBody
    @RequestMapping(value = "/index" )
    public ModelAndView index() {
        return new ModelAndView("/client/index.html");
    }

    @ResponseBody
    @RequestMapping(value = "/search" )
    public ModelAndView search() {
        return new ModelAndView("/client/search.html");
    }

    @ResponseBody
    @RequestMapping(value = "/search1" )
    public ModelAndView index1() {
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/client/search.jsp");
        return mav;
    }
}
