package com.acg12.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/3/14.
 */
@Controller
@RequestMapping(value = "/api/search")
public class AppSearchController {

//    @Resource(name = "searchServiceImpl")
//    private SearchService searchService;
//    @Resource(name = "resServiceImpl")
//    private ResService resService;
//
//    @ApiOperation(value = "搜索key", httpMethod = "GET", produces = "application/json")
//    @RequestMapping(value = "/key", method = {RequestMethod.GET})
//    public void searchKeyList(@ApiParam(name = "key", required = true, value = "encodeURI") @RequestParam("key") String key,
//                              HttpServletRequest request, HttpServletResponse response) throws Exception {
//        JSONArray jsonArray = resService.getMoeGirlSearchKeyList(key);
//        Result result = new Result();
//        if(jsonArray.length() == 0){
//            result.writeFailure("由于技术原因，暂时停止服务" ,response);
//        } else {
//            result.writeSucceed(jsonArray , response);
//        }
//    }
//
//    @ApiOperation(value = "搜索subject", httpMethod = "GET", produces = "application/json")
//    @RequestMapping(value = "/subject", method = {RequestMethod.GET})
//    public ResponseEntity<?> searchBgmKeyList(@ApiParam(name = "key", required = true, value = "encodeURI") @RequestParam("key") String key) throws Exception {
//        JSONObject jsonObject = resService.getBgmSearchKeyList(key);
//        if (jsonObject == null) {
//            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(Result.create200(jsonObject.toString()), HttpStatus.OK);
//        }
//    }
//
//    @ApiOperation(value = "搜索插画", httpMethod = "GET", produces = "application/json;charset=utf-8")
//    @RequestMapping(value = "/albums", method = {RequestMethod.GET}, produces = "application/json;charset=utf-8")
//    public ResponseEntity<?> querySearchAlbum(@ApiParam(name = "key", required = true, value = "搜索key") @RequestParam("key") String key,
//                                              @ApiParam(name = "page", required = true, value = "页") @RequestParam("page") String page) throws Exception {
//        List<Album> albumList = resService.getHuaBanSearchImages(key, page);
//        if (albumList == null || albumList.size() == 0) {
//            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(Result.create200(new ListDto<List<Album>>(albumList)), HttpStatus.OK);
//        }
//    }
//
//    @ApiOperation(value = "搜索画板", httpMethod = "GET", produces = "application/json;charset=utf-8")
//    @RequestMapping(value = "/palettes", method = {RequestMethod.GET} , produces = "application/json ;charset=utf-8")
//    public ResponseEntity<?> querySearchPalette(@ApiParam(name = "key", required = true, value = "搜索key") @RequestParam("key") String key,
//                                                @ApiParam(name = "page", required = true, value = "页") @RequestParam("page") String page) throws Exception {
//        List<Palette> paletteList = resService.getHuaBanSearchBoards(key, page);
//        if (paletteList == null || paletteList.size() == 0) {
//            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(Result.create200(new ListDto<List<Palette>>(paletteList)), HttpStatus.OK);
//        }
//    }
//
////    @RequestMapping(value = "/subject", method = {RequestMethod.GET})
////    public ResponseEntity<?> subjectSearch(@RequestParam Map<String,Object> map) throws Exception {
////        return new ResponseEntity<>(searchService.subjectSearchList(map), HttpStatus.OK);
////    }
////
////    @RequestMapping(value = "/character", method = {RequestMethod.GET})
////    @ResponseBody
////    public void characterSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
//////        BgmCrawler.getSubjectSearchList("夏娜" , 2 , 0);
////    }

}
