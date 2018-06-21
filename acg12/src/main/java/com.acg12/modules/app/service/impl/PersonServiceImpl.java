package com.acg12.modules.app.service.impl;

import com.acg12.modules.app.dao.person.PersonDao;
import com.acg12.modules.app.entity.po.person.PersonEntity;
import com.acg12.modules.app.service.PersonService;
import com.acg12.common.pagination.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
public class PersonServiceImpl implements PersonService {

    @Resource
    PersonDao personDao;

    @Override
    public List<PersonEntity> queryByPersonList(PageInfo pageInfo , String type, int gender, int bloodtype, String birthday) {
        List<PersonEntity> list = personDao.queryByPersonListPage(pageInfo ,type, gender, bloodtype, birthday);
        return list;
    }

    @Override
    public PersonEntity queryByPersonIdJoinDetail(int personId) {
        return personDao.queryByPersonIdJoinDetail(personId);
    }

}
