package com.acg12.dao.person;

import com.acg12.entity.po.person.PersonDetailEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface PersonDetailDao {

    int insert(PersonDetailEntity personDetailEntity);

    int insertList(List<PersonDetailEntity> list);

    int update(PersonDetailEntity personDetailEntity);

    List<PersonDetailEntity> queryList();

    List<PersonDetailEntity> queryByPersonId(int personId);
}