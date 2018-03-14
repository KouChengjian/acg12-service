package com.acg12.web.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/3/14.
 */
@Controller
@RequestMapping(value = "/search")
public class MainSearchController {

    @RequestMapping(value = "/subject", method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
    @ResponseBody
    public void subjectSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @RequestMapping(value = "/character", method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
    @ResponseBody
    public void characterSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

}
