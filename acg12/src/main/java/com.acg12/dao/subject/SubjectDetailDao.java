package com.acg12.dao.subject;

import com.acg12.entity.po.subject.SubjectDetailEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */
public interface SubjectDetailDao {

    @Insert("insert into acg_subject_detail (subjectDetailId, subjectId, otherTitle, otherValue) values (#{subjectDetailId,jdbcType=INTEGER},#{subjectId,jdbcType=INTEGER},#{otherTitle,jdbcType=VARCHAR},#{otherValue,jdbcType=VARCHAR})")
    @Options(useGeneratedKeys = true, keyProperty = "subjectDetailId")
    @ResultMap("subjectDetailResultMap")
    int insert(SubjectDetailEntity subjectEntity);

    int insertList(List<SubjectDetailEntity> list);

    int update(SubjectDetailEntity subjectEntity);

    List<SubjectDetailEntity> queryList();

    List<SubjectDetailEntity> queryBySubjectDetailId(int subjectDetailId);

    List<SubjectDetailEntity> queryBySubjectId(int subjectId);
}
