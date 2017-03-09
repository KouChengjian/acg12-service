package com.acg12.controller;

import com.acg12.utils.ReptileUtil;
import com.acg12.utils.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kouchengjian on 2017/3/6.
 */
@Controller
@RequestMapping(value = "/res")
public class ResourceController {

    @RequestMapping(value = "/p/anime" )
    @ResponseBody
    public void queryPictrueAnimes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            System.out.println("1");
            JSONObject content = ReptileUtil.getHomeContent();
            System.out.println("2");
            JSONObject json = new JSONObject();
            json.put("result", 200);
            json.put("desc",   "获取成功");
            json.put("data",   content);
            StringUtil.weiteData(response , json.toString());
        } catch (JSONException e){

        }

    }

    @RequestMapping(value = "/p/boards/anime" , method = {RequestMethod.POST, RequestMethod.GET})
    public void queryPictrueBoardsAnimes() throws Exception {

    }


    @RequestMapping(value = "/test")
    @ResponseBody
    public String lista(HttpServletRequest request) {

        return ReptileUtil.getUsernameById(1);
    }

}
