package com.acg12.dao;

import com.acg12.entity.po.SubjectEntity;
import com.acg12.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */
public interface SubjectDao {

    int insert(SubjectEntity subjectEntity);

    int update(SubjectEntity subjectEntity);

    List<SubjectEntity> queryList();

    SubjectEntity queryBySId(int sId);

    SubjectEntity queryBySubjectId(int subjectId);
}
