package com.acg12.dao.subject;

import com.acg12.entity.po.subject.SubjectCrtEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
public interface SubjectCrtDao {

    int insert(SubjectCrtEntity subjectEntity);

    int insertList(List<SubjectCrtEntity> list);

    int update(SubjectCrtEntity subjectEntity);

    SubjectCrtEntity queryBySubjectCrtId(int subjectCrtId);

    SubjectCrtEntity queryByCrt(@Param(value = "subjectId")int subjectId , @Param(value = "cId")int cId);

    List<SubjectCrtEntity> queryBySubjectId(int subjectId);

    List<SubjectCrtEntity> queryBySId(int subjectId);

    SubjectCrtEntity queryByCharacterId(int characterId);

    SubjectCrtEntity queryByPersonId(int personId);
}
