package com.acg12.modules.app.dao.subject;

import com.acg12.modules.app.entity.po.subject.SubjectOffprintEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/5/14.
 */
public interface SubjectOffprintDao {

    int insert(SubjectOffprintEntity subjectOffprintEntity);

    int insertList(List<SubjectOffprintEntity> list);

    SubjectOffprintEntity queryBySubjectOffprintId(int subjectOffprintId);

    SubjectOffprintEntity queryByOffprint(@Param(value = "sId")int sId , @Param(value = "id")int id);

    List<SubjectOffprintEntity> queryBySubjectId(int subjectId);

    List<SubjectOffprintEntity> queryBySId(int sId);
}
