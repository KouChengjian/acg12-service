package com.acg12.dao.person;

import com.acg12.entity.po.person.PersonEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface PersonDao {

    int insert(PersonEntity personEntity);

    int update(PersonEntity personEntity);

    List<PersonEntity> queryList();

    PersonEntity queryByPId(int pId);

    PersonEntity queryByPersonId(int personId);
}
