package com.acg12.web.api.subject;

import com.acg12.entity.dto.Result;
import com.acg12.service.subject.SubjectService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/18.
 */
@Controller
@RequestMapping(value = "/api/subject")
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

    @RequestMapping(value = "/character", method = {RequestMethod.GET})
    @ResponseBody
    public String characterSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "sdfasdasddas";
    }
}
