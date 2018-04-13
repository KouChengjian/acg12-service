package com.acg12.dao.subject;

import com.acg12.entity.po.subject.SubjectEntity;

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
