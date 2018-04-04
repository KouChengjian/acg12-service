package com.acg12.dao;

import com.acg12.entity.po.SubjectDetailEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */
public interface SubjectDetailDao {

    @Insert("insert into acg_subject_detail (detailId,subjectId,title,value) values (#{detailId,jdbcType=INTEGER},#{subjectId,jdbcType=INTEGER},#{title,jdbcType=VARCHAR},#{value,jdbcType=VARCHAR})")
    @Options(useGeneratedKeys = true, keyProperty = "detailId")
    @ResultMap("subjectDetailResultMap")
    int insert(SubjectDetailEntity subjectEntity);

    int insertList(List<SubjectDetailEntity> subjectList);

    int update(SubjectDetailEntity subjectEntity);

    List<SubjectDetailEntity> queryList();
}
