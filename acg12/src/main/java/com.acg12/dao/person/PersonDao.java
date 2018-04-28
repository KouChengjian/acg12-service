package com.acg12.dao.person;

import com.acg12.entity.po.person.PersonEntity;
import com.acg12.utils.pagination.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;

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


}
