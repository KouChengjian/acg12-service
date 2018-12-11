package com.acg12.controller.app;

import com.acg12.entity.dto.Acg12CharacterDto;
import com.acg12.entity.dto.Acg12PersonDto;
import com.acg12.entity.dto.Acg12SubjectDto;
import com.acg12.service.Acg12CharacterService;
import com.acg12.service.Acg12PersonService;
import com.acg12.service.Acg12SubjectService;
import com.acg12.utils.res.BgmResourceUtil;
import com.acg12.utils.result.ReturnJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/9/11 15:43
 * Description:
 */
@Controller
@RequestMapping("api/app/common/*")
public class AppCommonController {

    @Resource
    private Acg12SubjectService acg12SubjectService;
    @Resource
    private Acg12PersonService acg12PersonService;
    @Resource
    private Acg12CharacterService acg12CharacterService;

    //    @ResponseBody
    @RequestMapping(value = "/checkUpload", method = RequestMethod.GET)
    public String checkUpload(Long appType, String versionCode, Integer device) throws Exception {
//        return "sssssssssssssssssss";
        return "/login/index";
    }

    @RequestMapping(value = "/home/subject", method = {RequestMethod.GET})
    @ResponseBody
    public String homeSubjectInfo(@RequestParam("id") int sId) {
        Acg12SubjectDto acg12SubjectDto = acg12SubjectService.findSubjectDto(sId);
        if (acg12SubjectDto != null) {
            return ReturnJson.jsonStringOk(acg12SubjectDto);
        }

        // 外网获取
        acg12SubjectDto = BgmResourceUtil.getSubjectDto(sId);
        if (acg12SubjectDto != null) {
            acg12SubjectService.savaSubjectDto(acg12SubjectDto);
            return ReturnJson.jsonStringOk(acg12SubjectDto);
        }
        return ReturnJson.jsonStringError("无当前内容");
    }

    @RequestMapping(value = "/home/person", method = {RequestMethod.GET})
    @ResponseBody
    public String homePersonInfo(@RequestParam("id") int pId) {
        Acg12PersonDto acg12PersonDto = acg12PersonService.findPersonDto(pId);
        if (acg12PersonDto != null) {
            return ReturnJson.jsonStringOk(acg12PersonDto);
        }

        // 外网获取
        acg12PersonDto = BgmResourceUtil.getPersonDto(pId);
        if (acg12PersonDto != null) {
            acg12PersonService.savaPersonDto(acg12PersonDto);
            return ReturnJson.jsonStringOk(acg12PersonDto);
        }
        return ReturnJson.jsonStringError("无当前内容");
    }

    @RequestMapping(value = "/home/character", method = {RequestMethod.GET})
    @ResponseBody
    public String homeCharacterInfo(@RequestParam("id") int cId) {
        Acg12CharacterDto acg12CharacterDto = acg12CharacterService.findCharacterDto(cId);
        if (acg12CharacterDto != null) {
            return ReturnJson.jsonStringOk(acg12CharacterDto);
        }

        // 外网获取
        acg12CharacterDto = BgmResourceUtil.getCharacterDto(cId);
        if (acg12CharacterDto != null) {
            acg12CharacterService.savaCharacterDto(acg12CharacterDto);
            return ReturnJson.jsonStringOk(acg12CharacterDto);
        }
        return ReturnJson.jsonStringError("无当前内容");
    }
}
