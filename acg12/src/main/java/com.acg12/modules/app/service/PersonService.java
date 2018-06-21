package com.acg12.modules.app.service;

import com.acg12.common.pagination.PageInfo;
import com.acg12.modules.app.entity.po.person.PersonEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
public interface PersonService {

    List<PersonEntity> queryByPersonList(PageInfo pageInfo , String type, int gender, int bloodtype, String birthday);

    PersonEntity queryByPersonIdJoinDetail(int personId);
}
