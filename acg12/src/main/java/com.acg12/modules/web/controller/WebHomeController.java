package com.acg12.modules.web.controller;

import com.acg12.common.constant.Result;
import com.acg12.common.pagination.PageInfo;
import com.acg12.common.utils.StringUtil;
import com.acg12.modules.app.entity.dto.ListSumDto;
import com.acg12.modules.app.entity.po.character.CharacterEntity;
import com.acg12.modules.app.entity.po.person.PersonEntity;
import com.acg12.modules.app.entity.po.subject.SubjectEntity;
import com.acg12.modules.app.service.CharacterService;
import com.acg12.modules.app.service.PersonService;
import com.acg12.modules.app.service.SubjectService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mayn
 * Date: 2018/6/29
 * Time: 16:50
 * Description:
 */
@Controller
@RequestMapping(value = "/web/api")
public class WebHomeController {

    @Resource
    private CharacterService characterService;
    @Resource
    private PersonService personService;
    @Resource
    private SubjectService subjectService;

    @RequestMapping(value = "/character", method = {RequestMethod.GET})
    public ResponseEntity<?> queryByCharacterList(@RequestParam("type") String type,
                                                  @RequestParam("gender") int gender,
                                                  @RequestParam("bloodtype") int bloodtype,
                                                  @RequestParam("birthday") String birthday,
                                                  @RequestParam("page") int currentPage) throws Exception {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageInfo pageInfo = new PageInfo();
        int currentResult = (currentPage - 1) * pageInfo.getShowCount();
        pageInfo.setCurrentResult(currentResult);
        List<CharacterEntity> list = characterService.queryByCharacterList(pageInfo, type, gender, bloodtype, birthday);
        if (list == null || list.size() == 0) {
            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.create200(new ListSumDto<List<CharacterEntity>>(pageInfo.getTotalResult(), list)), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/person", method = {RequestMethod.GET})
    public ResponseEntity<?> queryByPersonList(@RequestParam("type") String type,
                                               @RequestParam("gender") int gender,
                                               @RequestParam("bloodtype") int bloodtype,
                                               @RequestParam("birthday") String birthday,
                                               @RequestParam("page") int currentPage) throws Exception {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageInfo pageInfo = new PageInfo();
        int currentResult = (currentPage - 1) * pageInfo.getShowCount();
        pageInfo.setCurrentResult(currentResult);
        List<PersonEntity> list = personService.queryByPersonList(pageInfo, type, gender, bloodtype, birthday);
        if (list == null || list.size() == 0) {
            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.create200(new ListSumDto<List<PersonEntity>>(pageInfo.getTotalResult(), list)), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/person/info", method = {RequestMethod.GET})
    public ResponseEntity<?> queryByPersonInfo(@RequestParam("personId") Integer personId) throws Exception {
        PersonEntity personEntity = personService.queryByPersonIdJoinDetail(personId);
        if (personEntity == null) {
            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.create200(personEntity), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/490", method = {RequestMethod.GET})
    public ResponseEntity<?> subjectSearch(@RequestParam Map<String, Object> map) throws Exception {
        JSONObject jsonObject = subjectService.getSubjectInfo(2);
        if (jsonObject == null) {
            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
        } else {
            return null;
        }
    }

    /**
     * 1、书籍 2、动画 3、音乐 4、游戏 6、三次元   将类型1分成 7、漫画 8、小说
     */
    @RequestMapping(value = "/subject", method = {RequestMethod.GET})
    public ResponseEntity<?> queryBySubjectList(@RequestParam Map<String, String> map, @RequestParam("page") int currentPage) throws Exception {
        String type = map.get("type");
        String typeName = map.get("typeName");
        String year = map.get("year");
        String month = map.get("month");
        String status = map.get("status");
        String platform = map.get("platform");

        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageInfo pageInfo = new PageInfo();
        int currentResult = (currentPage - 1) * pageInfo.getShowCount();
        pageInfo.setCurrentResult(currentResult);

        List<SubjectEntity> list = null;
        if (type.equals("2")) {
            list = subjectService.queryBySubjectList(pageInfo, type, StringUtil.getTypeName1(typeName), year, month, status);
        } else if (type.equals("4")) {
            list = subjectService.queryBySubjectList(pageInfo, type, StringUtil.getTypeName2(typeName), StringUtil.getPlatform(platform), year);
        } else if (type.equals("7")) {
            list = subjectService.queryBySubjectList(pageInfo, "1", "漫画", year, "", "");
        } else if (type.equals("8")) {
            list = subjectService.queryBySubjectList(pageInfo, "1", "小说", year, "", "");
        }

        if (list == null || list.size() == 0) {
            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.create200(new ListSumDto<List<SubjectEntity>>(pageInfo.getTotalResult(), list)), HttpStatus.OK);
        }
    }
}
