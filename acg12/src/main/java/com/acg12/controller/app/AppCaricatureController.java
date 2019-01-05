package com.acg12.controller.app;

import com.acg12.entity.dto.Acg12CaricatureChaptersDto;
import com.acg12.entity.dto.Acg12CaricatureDto;
import com.acg12.service.Acg12ResourceService;
import com.acg12.utils.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/12/29 13:55
 * Description:
 */
@Controller
@RequestMapping("api/app/caricature/*")
public class AppCaricatureController {

    @Resource
    private Acg12ResourceService acg12ResourceService;

    @ResponseBody
    @RequestMapping(value = "/chapters", method = {RequestMethod.POST})
    public Result caricatureInfo(int id, int type) {
        Acg12CaricatureDto caricatureDto = acg12ResourceService.kukeCaricatureInfo(id);
        if (caricatureDto == null) {
            return Result.error("数据为空");
        } else {
            return Result.ok(caricatureDto);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/chapters/list", method = {RequestMethod.POST})
    public Result caricatureChaptersInfo(int id, int index, int type) {
        Acg12CaricatureChaptersDto caricatureDto = acg12ResourceService.kukeCaricatureChapters(id, index);
        if (caricatureDto == null) {
            return Result.error("数据为空");
        } else {
            return Result.ok(caricatureDto);
        }
    }
}
