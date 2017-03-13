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

//    public static String getHomePaletteAlbumMore(HttpServletRequest request,
//                                                 HttpServletResponse response) {
//        String boardId = request.getParameter("boardId");
//        String max = request.getParameter("max");
//        final String paletteContent = HttpUtlis.getPaletteAblumHtmlString(boardId,max);
//        if(paletteContent != null && !paletteContent.isEmpty()){
//            return StringUtil.weiteData(paletteContent,Constant.SUCCESS,response);
//        } else {
//            return StringUtil.weiteData(paletteContent,Constant.ERROR,response);
//        }
//    }
//
//    public static String getHomeVideoMore(HttpServletRequest request,
//                                          HttpServletResponse response) {
//        String type = request.getParameter("type");
//        String page = request.getParameter("page");
//        String url = StringUtil.getMoreVideoUrl(type);
//        final String videoContent = HttpUtlis.getMoreVedio(url , page);
//        if(videoContent != null && !videoContent.isEmpty()){
//            return StringUtil.weiteData(videoContent,Constant.SUCCESS,response);
//        } else {
//            return StringUtil.weiteData(videoContent,Constant.ERROR,response);
//        }
//    }
//
//    public static String getHomeVideoInfo(HttpServletRequest request,
//                                          HttpServletResponse response) {
//        String av = request.getParameter("av");
//        final String videoContent = HttpUtlis.getHomeVideoInfo(av);
//        if(videoContent != null && !videoContent.isEmpty()){
//            return StringUtil.weiteData(videoContent,Constant.SUCCESS,response);
//        } else {
//            return StringUtil.weiteData(videoContent,Constant.ERROR,response);
//        }
//    }
//
//    public static String getFindContent(HttpServletRequest request,HttpServletResponse response){
//        String page = request.getParameter("page");
//        final String findContent  = HttpUtlis.getFind(page);
//        if(findContent != null && !findContent.isEmpty()){
//            return StringUtil.weiteData(findContent,Constant.SUCCESS,response);
//        } else {
//            return StringUtil.weiteData(findContent,Constant.ERROR,response);
//        }
//    }
//
//    public static String getFindInfo(HttpServletRequest request,HttpServletResponse response){
//        String av = request.getParameter("av");
//        final String findContent  = HttpUtlis.getFindInfo(av);
//        if(findContent != null && !findContent.isEmpty()){
//            return StringUtil.weiteData(findContent,Constant.SUCCESS,response);
//        } else {
//            return StringUtil.weiteData(findContent,Constant.ERROR,response);
//        }
//    }
//
//    public static void getSearchAlbum(HttpServletRequest request,HttpServletResponse response){
//        String key = request.getParameter("key");
//        String page = request.getParameter("page");
//        final String content = HttpUtlis.getSearchAlbum(key, page);
//        if(content != null && !content.isEmpty()){
//            StringUtil.weiteData(content,Constant.SUCCESS,response);
//        } else {
//            StringUtil.weiteData(content,Constant.ERROR,response);
//        }
//    }
//
//    public static void getSearchPalette(HttpServletRequest request,HttpServletResponse response){
//        String key = request.getParameter("key");
//        String page = request.getParameter("page");
//        final String content = HttpUtlis.getSearchPalette(key, page);
//        if(content != null && !content.isEmpty()){
//            StringUtil.weiteData(content,Constant.SUCCESS,response);
//        } else {
//            StringUtil.weiteData(content,Constant.ERROR,response);
//        }
//    }
//
//    public static void getSearchVideo(HttpServletRequest request,HttpServletResponse response){
//        String key = request.getParameter("key");
//        String page = request.getParameter("page");
//        final String content = HttpUtlis.getSearchVideo(key, page);
//        if(content != null && !content.isEmpty()){
//            StringUtil.weiteData(content,Constant.SUCCESS,response);
//        } else {
//            StringUtil.weiteData(content,Constant.ERROR,response);
//        }
//    }
//
//    public static void getSearchBangunmi(HttpServletRequest request,HttpServletResponse response){
//        String key = request.getParameter("key");
//        String page = request.getParameter("page");
//        final String content = HttpUtlis.getSearchBangunmi(key, page);
//        if(content != null && !content.isEmpty()){
//            StringUtil.weiteData(content,Constant.SUCCESS,response);
//        } else {
//            StringUtil.weiteData(content,Constant.ERROR,response);
//        }
//    }
//
//    public static String getFindInfoAv(HttpServletRequest request,HttpServletResponse response){
//        String number = request.getParameter("number");
//        final String findContent  = HttpUtlis.getFindInfoAv(number);
//        if(findContent != null && !findContent.isEmpty()){
//            return StringUtil.weiteData(findContent,Constant.SUCCESS,response);
//        } else {
//            return StringUtil.weiteData(findContent,Constant.ERROR,response);
//        }
//    }
//
//    public static void getPlayUrl(HttpServletRequest request,HttpServletResponse response){
//        String action = request.getParameter("action");
//        String av = request.getParameter("av");
//        if(action.equals("bangumi")){
//            av  = HttpUtlis.getFindInfoAv(av);
//        }
//        final String content = HttpUtlis.getPlayUrl(av);
//
//        if(content != null && !content.isEmpty()){
//            StringUtil.weiteData(content,Constant.SUCCESS,response);
//        } else {
//            StringUtil.weiteData(content,Constant.ERROR,response);
//        }
//    }


}
