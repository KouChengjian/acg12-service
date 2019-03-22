package com.acg12.controller.app;

import com.acg12.controller.AppBaseController;
import com.acg12.entity.dto.Acg12AlbumDto;
import com.acg12.entity.dto.Acg12CaricatureDto;
import com.acg12.entity.dto.Acg12PaletteDto;
import com.acg12.entity.dto.UserDao;
import com.acg12.service.Acg12CollectCaricatureService;
import com.acg12.service.Acg12ResourceService;
import com.acg12.utils.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */
@Controller
@RequestMapping("api/app/search/*")
public class AppSearchController extends AppBaseController {

    @Resource
    private Acg12ResourceService acg12ResourceService;
    @Resource
    private Acg12CollectCaricatureService acg12CollectCaricatureService;

    @ResponseBody
    @RequestMapping(value = "/key", method = {RequestMethod.POST})
    public Result searchKeyList(String key) {
        String content = acg12ResourceService.getMengNiangSearchKeyList(key);
        if (content == null || content.isEmpty()) {
            return Result.error("数据为空");
        } else {
            return Result.ok().put("data", content);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/subject", method = {RequestMethod.POST})
    public Result searchSubject(String key) {
        String content = acg12ResourceService.getBgmSearchKeyList(key);
        if (content == null || content.isEmpty()) {
            return Result.error("数据为空");
        } else {
            return Result.ok().put("data", content);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/albumList", method = {RequestMethod.POST})
    public Result albumList(String key, String page) {
        List<Acg12AlbumDto> albumList = acg12ResourceService.getHuaBanSearchImages(key, page);
        if (albumList == null || albumList.size() == 0) {
            return Result.error("数据为空");
        } else {
            return Result.ok(albumList);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/paletteList", method = {RequestMethod.POST})
    public Result paletteList(String key, String page) {
        List<Acg12PaletteDto> paletteList = acg12ResourceService.getHuaBanSearchBoards(key, page);
        if (paletteList == null || paletteList.size() == 0) {
            return Result.error("数据为空");
        } else {
            return Result.ok(paletteList);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/caricatureList", method = {RequestMethod.POST})
    public Result caricatureList(String key, String page) {
        List<Acg12CaricatureDto> caricatureDtoList = acg12ResourceService.kukeSearch(key);
        if (caricatureDtoList == null || caricatureDtoList.size() == 0) {
            return Result.error("数据为空");
        } else {
            UserDao loginUser = getCurrentUser();
            if (loginUser != null) {
                caricatureDtoList = acg12CollectCaricatureService.buildHasCollectToCaricature(caricatureDtoList, loginUser.getId());
            }
            return Result.ok(caricatureDtoList);
        }
    }
}
