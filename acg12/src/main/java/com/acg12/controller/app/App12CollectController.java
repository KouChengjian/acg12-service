package com.acg12.controller.app;

import com.acg12.controller.AppBaseController;
import com.acg12.service.Acg12CollectAlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2019/1/16 18:03
 * Description:
 */
@Controller
@RequestMapping("api/app/collect/*")
public class App12CollectController extends AppBaseController {

    @Resource
    private Acg12CollectAlbumService acg12CollectAlbumService;

//    @ResponseBody
//    @RequestMapping(value = "/album/list", method = {RequestMethod.POST})
//    public Result albumList(int id, int type) {
//        Map<String, Object> parameter = new HashMap<String, Object>();
////        parameter.put("pageNumber", (pageNumber - 1) * pageSize);
////        parameter.put("pageSize", pageSize);
//        parameter.put("pageNumber", 0);
//        parameter.put("pageSize", 10);
//        parameter.put("status", 1);
//        parameter.put("order", " id desc");
//        Acg12CaricatureDto caricatureDto = acg12CollectAlbumService.findListByPage(parameter);
//        if (caricatureDto == null) {
//            return Result.error("数据为空");
//        } else {
//            return Result.ok(caricatureDto);
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/album/add", method = {RequestMethod.POST})
//    public Result albumAdd(int id, int type) {
//        Acg12CaricatureDto caricatureDto = acg12ResourceService.kukeCaricatureInfo(id);
//        if (caricatureDto == null) {
//            return Result.error("数据为空");
//        } else {
//            return Result.ok(caricatureDto);
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/album/del", method = {RequestMethod.POST})
//    public Result albumDel(int id, int type) {
//        Acg12CaricatureDto caricatureDto = acg12ResourceService.kukeCaricatureInfo(id);
//        if (caricatureDto == null) {
//            return Result.error("数据为空");
//        } else {
//            return Result.ok(caricatureDto);
//        }
//    }
}
