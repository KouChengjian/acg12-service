package com.acg12.web.api.subject;

import com.acg12.entity.dto.Result;
import com.acg12.entity.dto.subject.SubjectDto;
import com.acg12.entity.po.subject.SubjectEntity;
import com.acg12.service.subject.SubjectService;
import com.acg12.utils.pagination.PageInfo;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<?> subjectSearch(@RequestParam Map<String,Object> map) throws Exception {
        JSONObject jsonObject = subjectService.getSubjectInfo(2);
        if(jsonObject == null){
            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
        } else {
            return null;
        }
    }

    /**
     *
     * @param type  1、书籍 2、动画 3、音乐 4、游戏 6、三次元
     * @param typeName
     * @param year
     * @param month
     * @param status
     * @param currentPage
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/subject", method = {RequestMethod.GET})
    public ResponseEntity<?> queryBySubjectList(@RequestParam("type") String type,
                                                @RequestParam("typeName") String typeName,
                                                  @RequestParam("year") String year,
                                                  @RequestParam("month") String month,
                                                  @RequestParam("status") String status,
                                                  @RequestParam("page") int currentPage) throws Exception {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        PageInfo pageInfo = new PageInfo();
        int currentResult = (currentPage - 1) * pageInfo.getShowCount();
        pageInfo.setCurrentResult(currentResult);
        List<SubjectEntity> list = subjectService.queryBySubjectList(pageInfo, type, typeName, year, month, status);
        if (list == null || list.size() == 0) {
            return new ResponseEntity<>(Result.create202(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Result.create200(new SubjectDto(pageInfo.getTotalResult(), list)), HttpStatus.OK);
        }
    }
}
