package com.acg12.dao;

import com.acg12.entity.po.SubjectEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */
public interface SubjectDao {

    int insert(SubjectEntity subjectEntity);

    int insertList(List<SubjectEntity> subjectList);

    int update(SubjectEntity subjectEntity);

    List<SubjectEntity> queryList();
}
