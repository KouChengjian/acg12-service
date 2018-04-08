package com.acg12.dao;

import com.acg12.entity.po.PersonDetailEntity;
import com.acg12.entity.po.SubjectDetailEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface PersonDetailDao {

    int insert(PersonDetailEntity personDetailEntity);

    int insertList(List<PersonDetailEntity> list);

    int update(PersonDetailEntity personDetailEntity);

    List<PersonDetailEntity> queryList();

    List<PersonDetailEntity> queryById(int personId);
}
