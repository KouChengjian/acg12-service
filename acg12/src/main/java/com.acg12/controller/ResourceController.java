package com.acg12.controller;

import com.acg12.beans.Result;
import com.acg12.conf.Constant;
import com.acg12.service.ResourceServiceImpl;
import com.acg12.service.base.ResourceService;
import com.acg12.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kouchengjian on 2017/3/6.
 */
@Api(value = "ResourceController"  , description = "资源控制" )
@Controller
@RequestMapping(value = "/res")
public class ResourceController {

    @Resource
    private ResourceServiceImpl resourceService;

    @ApiOperation(value = "首页", httpMethod = "GET", produces = "application/json")
//    @ApiResponse(code = 200, message = "success", response = Result.class)
    @RequestMapping(value = "/index" , method = {RequestMethod.GET} , produces = "application/json ;charset=utf-8" )
    @ResponseBody
    public String queryIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("1");
        JSONObject content = resourceService.getIndex();
        System.out.println("2");
        String result = StringUtil.result(content);
//        StringUtil.outputStream(response , result);
//        result result = new
//        result.setResult(Constant.HTTP_RESULT_ERROR_PARAM);
//        result.setDesc("请求参数为空");
//        StringUtil.outputStream(response , StringUtil.result(result));
        return result;
    }

    @ApiOperation(value = "获取插画", httpMethod = "GET", produces = "application/json")
//    @ApiResponse(code = 200, message = "success", response = Result.class)
    @RequestMapping(value = "/p/album" ,method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
    @ResponseBody
    public void queryPictrueAlbum(@ApiParam(name = "max", required = true, value = "图片的id") @RequestParam("max") String max,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject content = resourceService.getAlbumList(max);
        Result result = new Result();
        if(content == null) {
            result.setResult(Constant.HTTP_RESULT_ERROR);
            result.setDesc("获取错误");
        } else {
            result.setResult(Constant.HTTP_RESULT_SUCCEED);
            result.setDesc("获取成功");
            result.setData(content);
        }
        StringUtil.outputStream(response , StringUtil.result(result));
    }

    @RequestMapping(value = "/p/boards" , method = {RequestMethod.GET})
    public void queryPictrueBoards(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String max = request.getParameter("max");
        JSONObject content = resourceService.getBoardsList(max);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);

    }

    @RequestMapping(value = "/p/boards/album" , method = {RequestMethod.GET})
    public void queryPictrueBoardsAlbum(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String max = request.getParameter("max");
        String boardId = request.getParameter("boardId");
        JSONObject content = resourceService.getBoardsToAlbumList(boardId , max);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);

    }

    @RequestMapping(value = "/v" , method = {RequestMethod.GET})
    public void queryVideoTypeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String type = request.getParameter("type");
        String page = request.getParameter("page");
        JSONObject content = resourceService.getVideoTypeList(type , page);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);

    }

    @RequestMapping(value = "/v/info" , method = {RequestMethod.GET})
    public void queryVideoTypeInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String av = request.getParameter("av");
        JSONObject content = resourceService.getVideoTypeInfo(av);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);
    }

    @RequestMapping(value = "/v/dangumi" , method = {RequestMethod.GET})
    public void queryDangumiList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = request.getParameter("page");
        JSONObject content = resourceService.getDangumiList(page);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);
    }

    @RequestMapping(value = "/v/dangumi/info" , method = {RequestMethod.GET})
    public void queryDangumiInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String av = request.getParameter("bmId");
        JSONObject content = resourceService.getDangumiInfo(av);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);
    }

    @RequestMapping(value = "/v/dangumi/id" , method = {RequestMethod.GET})
    public void queryDangumiAV(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("number");
        JSONObject content = resourceService.getDangumiAV(id);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);
    }

    @RequestMapping(value = "/v/search/album" , method = {RequestMethod.GET})
    public void querySearchAlbum(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String key = request.getParameter("key");
        String page = request.getParameter("page");
        JSONObject content = resourceService.getSearchAlbum(key , page);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);
    }

    @RequestMapping(value = "/v/search/palette" , method = {RequestMethod.GET})
    public void querySearchPalette(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String key = request.getParameter("key");
        String page = request.getParameter("page");
        JSONObject content = resourceService.getSearchBoards(key , page);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);
    }

    @RequestMapping(value = "/v/search/video" , method = {RequestMethod.GET})
    public void querySearchVideo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String key = request.getParameter("key");
        String page = request.getParameter("page");
        JSONObject content = resourceService.getSearchVideo(key , page);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);
    }

    @RequestMapping(value = "/v/search/bangunmi" , method = {RequestMethod.GET})
    public void querySearchBangunmi(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String key = request.getParameter("key");
        String page = request.getParameter("page");
        JSONObject content = resourceService.getSearchDangumi(key , page);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);
    }

    @RequestMapping(value = "/v/playurl" , method = {RequestMethod.GET})
    public void queryPlayUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String action = request.getParameter("action");
        String av = request.getParameter("av");
        if(action.equals("bangumi")){
            //av  = HttpUtlis.getFindInfoAv(av);
        }
        JSONObject content = resourceService.getPlayInfo(av);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response , result);
    }


}
