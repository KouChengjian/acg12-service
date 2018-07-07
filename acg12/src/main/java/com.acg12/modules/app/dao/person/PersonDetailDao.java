package com.acg12.modules.app.dao.person;


import com.acg12.modules.app.entity.po.person.PersonDetailEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface PersonDetailDao {

    int insert(PersonDetailEntity personDetailEntity);

    int insertList(List<PersonDetailEntity> list);

    int update(PersonDetailEntity personDetailEntity);

    int delete(int personDetailId);

    List<PersonDetailEntity> queryList();

    List<PersonDetailEntity> queryByPersonId(int personId);
}
