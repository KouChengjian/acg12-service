package com.acg12.controller;

import com.acg12.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kouchengjian on 2017/3/2.
 */
@Controller
@RequestMapping(value = "/save")
public class test {

    @Autowired
    private ItemsService itemsService;

    /**
     * 商品查询
     */
    @RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String queryItems() throws Exception {
        return "sssss";
    }
}
