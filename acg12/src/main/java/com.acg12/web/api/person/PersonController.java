package com.acg12.web.api.person;

import com.acg12.entity.dto.PersonList;
import com.acg12.entity.dto.Result;
import com.acg12.entity.po.person.PersonEntity;
import com.acg12.service.person.PersonService;
import com.acg12.utils.pagination.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
@Controller
@RequestMapping(value = "/api")
public class PersonController {

    @Resource
    PersonService personService;

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
            return new ResponseEntity<>(Result.create200(new PersonList(pageInfo.getTotalResult(), list)), HttpStatus.OK);
        }
    }
}