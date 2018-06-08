package com.acg12.modules.app.controller;

import com.acg12.modules.app.service.search.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/14.
 */
@Controller
@RequestMapping(value = "/api/search")
public class SearchController {

    @Resource
    private SearchService searchService;

    @RequestMapping(value = "/subject", method = {RequestMethod.GET})
    public ResponseEntity<?> subjectSearch(@RequestParam Map<String,Object> map) throws Exception {
        return new ResponseEntity<>(searchService.subjectSearchList(map), HttpStatus.OK);
    }

    @RequestMapping(value = "/character", method = {RequestMethod.GET})
    @ResponseBody
    public void characterSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        SearchUtli.getSubjectSearchList("夏娜" , 2 , 0);
    }

}
