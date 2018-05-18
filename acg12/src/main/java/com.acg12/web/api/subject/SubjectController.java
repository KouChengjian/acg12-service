package com.acg12.web.api.subject;

import com.acg12.entity.dto.Result;
import com.acg12.entity.dto.subject.SubjectListDto;
import com.acg12.entity.po.subject.SubjectEntity;
import com.acg12.service.subject.SubjectService;
import com.acg12.utils.StringUtil;
import com.acg12.utils.pagination.PageInfo;
import com.mysql.jdbc.log.LogUtils;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/18.
 */
@Controller
@RequestMapping(value = "/api")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

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
            return new ResponseEntity<>(Result.create200(new SubjectListDto(pageInfo.getTotalResult(), list)), HttpStatus.OK);
        }
    }
}
