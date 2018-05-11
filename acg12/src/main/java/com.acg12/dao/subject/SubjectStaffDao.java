package com.acg12.dao.subject;

import com.acg12.entity.po.subject.SubjectStaffEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
public interface SubjectStaffDao {

    int insert(SubjectStaffEntity subjectEntity);

    int insertList(List<SubjectStaffEntity> list);

    int update(SubjectStaffEntity subjectEntity);

    SubjectStaffEntity queryBySubjectStaffId(int subjectStaffId);

    SubjectStaffEntity queryByStaffByPId(@Param(value = "subjectId")int subjectId , @Param(value = "pId")int pId, @Param(value = "job")String job);

    List<SubjectStaffEntity> queryBySubjectId(int subjectId);

    List<SubjectStaffEntity> queryByPersonId(int personId);
}
