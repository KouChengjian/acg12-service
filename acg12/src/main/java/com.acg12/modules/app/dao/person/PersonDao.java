package com.acg12.modules.app.dao.person;

import com.acg12.common.pagination.PageInfo;
import com.acg12.modules.app.entity.po.person.PersonEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface PersonDao {

    int insert(PersonEntity personEntity);

    int update(PersonEntity personEntity);

    List<PersonEntity> queryList();

    List<PersonEntity> queryByPersonListPage(@Param("page") PageInfo page, @Param("type") String type, @Param("gender") int gender, @Param("bloodtype") int bloodtype, @Param("birthday") String birthday);

    PersonEntity queryByPId(int pId);

    PersonEntity queryByPersonId(int personId);

    PersonEntity queryByPersonIdJoinDetail(int personId);

}
