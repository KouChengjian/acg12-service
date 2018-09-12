package com.acg12.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/9/11 15:43
 * Description:
 */
@Controller
@RequestMapping("api/app/common/*")
public class AppCommonController {

//    @ResponseBody
    @RequestMapping(value = "/checkUpload", method = RequestMethod.GET)
    public String checkUpload(Long appType, String versionCode, Integer device) throws Exception {
//        return "sssssssssssssssssss";
        return "/login/index";
    }
}
