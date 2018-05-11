package com.acg12.service.person.impl;

import com.acg12.dao.person.PersonDao;
import com.acg12.entity.po.person.PersonEntity;
import com.acg12.service.person.PersonService;
import com.acg12.utils.pagination.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
public class PersonServiceImpl implements PersonService {

    @Resource
    PersonDao personDao;

    @Override
    public List<PersonEntity> queryByPersonList(PageInfo pageInfo ,String type, int gender, int bloodtype, String birthday) {
        List<PersonEntity> list = personDao.queryByPersonListPage(pageInfo ,type, gender, bloodtype, birthday);
        return list;
    }

    @Override
    public PersonEntity queryByPersonIdJoinDetail(int personId) {
        return personDao.queryByPersonIdJoinDetail(personId);
    }

}
