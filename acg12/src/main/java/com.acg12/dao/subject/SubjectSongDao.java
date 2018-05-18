package com.acg12.dao.subject;

import com.acg12.entity.po.subject.SubjectSongEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/5/14.
 */
public interface SubjectSongDao {

    int insert(SubjectSongEntity subjectSongEntity);

    int insertList(List<SubjectSongEntity> list);

    SubjectSongEntity queryBySong(@Param(value = "sId")int sId , @Param(value = "title")String title);

    List<SubjectSongEntity> queryBySubjectId(int subjectId);

    List<SubjectSongEntity> queryBySId(int sId);
}
