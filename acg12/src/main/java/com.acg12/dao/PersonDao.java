package com.acg12.dao;

import com.acg12.entity.po.PersonEntity;
import com.acg12.entity.po.SubjectEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface PersonDao {

    int insert(PersonEntity personEntity);

    int update(PersonEntity personEntity);

    List<PersonEntity> queryList();

    PersonEntity queryByPId(int personId);

    PersonEntity queryByPersonId(int personId);
}
