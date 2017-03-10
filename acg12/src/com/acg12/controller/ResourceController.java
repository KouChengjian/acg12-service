package com.acg12.controller;

import com.acg12.service.ResourceService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kouchengjian on 2017/3/6.
 */
@Controller
@RequestMapping(value = "/res")
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    @RequestMapping(value = "/index" , method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public void queryIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("1");
        JSONObject content = resourceService.getIndex();
        System.out.println("2");
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);
    }

    @RequestMapping(value = "/p/anime" ,method = {RequestMethod.GET})
    @ResponseBody
    public void queryPictrueAnimes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String max = request.getParameter("max");
        JSONObject content = resourceService.getAlbumList(max);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);
    }

    @RequestMapping(value = "/p/boards/anime" , method = {RequestMethod.GET})
    public void queryPictrueBoardsAnimes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String max = request.getParameter("max");
        JSONObject content = resourceService.getBoardsList(max);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);

    }




}
