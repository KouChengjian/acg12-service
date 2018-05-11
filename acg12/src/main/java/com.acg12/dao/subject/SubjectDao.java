package com.acg12.dao.subject;

import com.acg12.entity.po.subject.SubjectEntity;
import com.acg12.utils.pagination.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */
public interface SubjectDao {

    int insert(SubjectEntity subjectEntity);

    int update(SubjectEntity subjectEntity);

    List<SubjectEntity> queryList();

    List<SubjectEntity> queryBySubjectListPage(@Param("page") PageInfo page, @Param("type") String type, @Param("typeName") String typeName, @Param("year") String year, @Param("month") String month, @Param("status") String status);

    SubjectEntity queryBySId(int sId);

    SubjectEntity queryBySubjectId(int subjectId);
}
