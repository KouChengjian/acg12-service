package com.acg12.web.common;

import com.acg12.entity.dto.Result;
import com.acg12.conf.Constant;
import com.acg12.service.ResServiceImpl;
import com.acg12.service.base.ResService;
import com.acg12.utils.StringUtil;
import com.acg12.utils.UrlEncoderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by kouchengjian on 2017/3/6.
 */
@Api(value = "ResourceController", description = "资源控制")
@Controller
@RequestMapping(value = "/res")
public class ResController {

    @Resource
    private ResService resourceService;

    /**
     * --------------------------------------自定义资源-------------------------------------------
     */
    @ApiOperation(value = "首页", httpMethod = "GET", produces = "application/json")
    @RequestMapping(value = "/index", method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
    @ResponseBody
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject content = resourceService.getIndex();
        Result result = new Result();
        if(content.length() == 0){
            result.writeFailure("由于技术原因，暂时停止服务" ,response);
        } else {
            result.writeSucceed(content , response);
        }
    }

    /**
     * --------------------------------------花瓣网资源-------------------------------------------
     */
    @ApiOperation(value = "获取插画", httpMethod = "GET", produces = "application/json")
    @RequestMapping(value = "/p/album", method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
    @ResponseBody
    public void queryPictrueAlbum(@ApiParam(name = "max", required = true, value = "图片的id") @RequestParam("max") String max,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject content = resourceService.getAlbumList(max);
        Result result = new Result();
        if (content == null) {
            result.setResult(Constant.HTTP_RESULT_ERROR);
            result.setDesc("获取错误");
        } else {
            result.setResult(Constant.HTTP_RESULT_SUCCEED);
            result.setDesc("获取成功");
            result.setData(content);
        }
        result.write(response);
    }

    @ApiOperation(value = "获取插画", httpMethod = "GET", produces = "application/json")
    @RequestMapping(value = "/p/boards", method = {RequestMethod.GET})
    public void queryPictrueBoards(@ApiParam(name = "max", required = true, value = "画板id") @RequestParam("max") String max,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject content = resourceService.getBoardsList(max);
        Result result = new Result();
        if (content == null) {
            result.setResult(Constant.HTTP_RESULT_ERROR);
            result.setDesc("获取错误");
        } else {
            result.setResult(Constant.HTTP_RESULT_SUCCEED);
            result.setDesc("获取成功");
            result.setData(content);
        }
        StringUtil.outputStream(response, StringUtil.result(result));
    }

    @RequestMapping(value = "/p/boards/album", method = {RequestMethod.GET})
    public void queryPictrueBoardsAlbum(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String max = request.getParameter("max");
        String boardId = request.getParameter("boardId");
        JSONObject content = resourceService.getBoardsToAlbumList(boardId, max);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response, result);

    }

    @ApiOperation(value = "搜索插画", httpMethod = "GET", produces = "application/json;charset=utf-8")
    @RequestMapping(value = "/search/album", method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
    public void querySearchAlbum(@ApiParam(name = "key", required = true, value = "搜索key") @RequestParam("key") String key,
                                 @ApiParam(name = "page", required = true, value = "页") @RequestParam("page") String page,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONArray content = resourceService.getSearchAlbum(key, page);
        Result result = new Result();
        if(content == null || content.length() == 0){
            result.writeFailure("由于技术原因，暂时停止服务" ,response);
        } else {
            result.writeSucceed(content , response);
        }
    }

    @ApiOperation(value = "搜索画板", httpMethod = "GET", produces = "application/json;charset=utf-8")
    @RequestMapping(value = "/search/palette", method = {RequestMethod.GET} , produces = "application/json ;charset=utf-8")
    public void querySearchPalette(@ApiParam(name = "key", required = true, value = "搜索key") @RequestParam("key") String key,
                                   @ApiParam(name = "page", required = true, value = "页") @RequestParam("page") String page,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONArray content = resourceService.getSearchBoards(key, page);
        Result result = new Result();
        if(content == null || content.length() == 0){
            result.writeFailure("由于技术原因，暂时停止服务" ,response);
        } else {
            result.writeSucceed(content , response);
        }
    }

    /**
     * --------------------------------------动漫之家资源--------------------------------------------
     */

    @ApiOperation(value = "每日资讯", httpMethod = "GET", produces = "application/json")
    @RequestMapping(value = "/news", method = {RequestMethod.GET}, produces = "application/json ;charset=utf-8")
    @ResponseBody
    public void getNews(@ApiParam(name = "page", required = true, value = "页") @RequestParam("page") String page,
                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONArray content = resourceService.getNews(page);
        Result result = new Result();
        if(content == null || content.length() == 0){
            result.writeFailure("由于技术原因，暂时停止服务" ,response);
        } else {
            result.writeSucceed(content , response);
        }
    }


    /**
     * --------------------------------------萌娘百科资源--------------------------------------------
     */
    @ApiOperation(value = "搜索key", httpMethod = "GET", produces = "application/json")
    @RequestMapping(value = "search/key", method = {RequestMethod.GET})
    public void searchKeyList(@ApiParam(name = "key", required = true, value = "encodeURI") @RequestParam("key") String key,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONArray jsonArray = resourceService.getSearchKeyList(key);
        Result result = new Result();
        if(jsonArray.length() == 0){
            result.writeFailure("由于技术原因，暂时停止服务" ,response);
        } else {
            result.writeSucceed(jsonArray , response);
        }
    }

    @ApiOperation(value = "搜索key", httpMethod = "GET", produces = "application/json")
    @RequestMapping(value = "search/info", method = {RequestMethod.GET})
    public void searchKeyInfo(@ApiParam(name = "key", required = true, value = "encodeURI") @RequestParam("key") String key,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONArray jsonArray = resourceService.getSearchKeyList(key);
        Result result = new Result();
        if(jsonArray.length() == 0){
            result.writeFailure("由于技术原因，暂时停止服务" ,response);
        } else {
            result.writeSucceed(jsonArray , response);
        }
    }


    /**
     * --------------------------------------bilibili资源--------------------------------------------
     */

    @RequestMapping(value = "/v", method = {RequestMethod.GET})
    public void queryVideoTypeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String type = request.getParameter("type");
        String page = request.getParameter("page");
        JSONObject content = resourceService.getVideoTypeList(type, page);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response, result);

    }

    @RequestMapping(value = "/v/info", method = {RequestMethod.GET})
    public void queryVideoTypeInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String av = request.getParameter("av");
        JSONObject content = resourceService.getVideoTypeInfo(av);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response, result);
    }

    @RequestMapping(value = "/v/dangumi", method = {RequestMethod.GET})
    public void queryDangumiList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = request.getParameter("page");
        JSONObject content = resourceService.getDangumiList(page);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response, result);
    }

    @RequestMapping(value = "/v/dangumi/info", method = {RequestMethod.GET})
    public void queryDangumiInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String av = request.getParameter("bmId");
        JSONObject content = resourceService.getDangumiInfo(av);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response, result);
    }

    @RequestMapping(value = "/v/dangumi/id", method = {RequestMethod.GET})
    public void queryDangumiAV(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("number");
        JSONObject content = resourceService.getDangumiAV(id);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response, result);
    }

    @RequestMapping(value = "/v/search/video", method = {RequestMethod.GET})
    public void querySearchVideo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String key = request.getParameter("key");
        String page = request.getParameter("page");
        JSONObject content = resourceService.getSearchVideo(key, page);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response, result);
    }

    @RequestMapping(value = "/v/search/bangunmi", method = {RequestMethod.GET})
    public void querySearchBangunmi(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String key = request.getParameter("key");
        String page = request.getParameter("page");
        JSONObject content = resourceService.getSearchDangumi(key, page);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response, result);
    }

    @RequestMapping(value = "/v/playurl", method = {RequestMethod.GET})
    public void queryPlayUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String action = request.getParameter("action");
        String av = request.getParameter("av");
        if (action.equals("bangumi")) {
            //av  = HttpUtlis.getFindInfoAv(av);
        }
        JSONObject content = resourceService.getPlayInfo(av);
        String result = StringUtil.result(content);
        StringUtil.outputStream(response, result);
    }


}
