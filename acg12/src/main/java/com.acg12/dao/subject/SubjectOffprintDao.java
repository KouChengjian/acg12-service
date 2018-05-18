package com.acg12.dao.subject;

import com.acg12.entity.po.subject.SubjectCrtEntity;
import com.acg12.entity.po.subject.SubjectOffprintEntity;
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
