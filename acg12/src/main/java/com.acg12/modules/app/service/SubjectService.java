package com.acg12.modules.app.service;

import com.acg12.common.pagination.PageInfo;
import com.acg12.modules.app.entity.dto.subject.SubjectInfoDto;
import com.acg12.modules.app.entity.po.subject.SubjectEntity;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */
public interface SubjectService {

    List<SubjectEntity> queryBySubjectList(PageInfo pageInfo, String type, String typeName, String year, String month, String status);

    List<SubjectEntity> queryBySubjectList(PageInfo pageInfo, String type, String typeName, String platform, String year);

    SubjectInfoDto queryBySIdJoinDetail(int sId);

    JSONObject getSubjectInfo(int subjectId) throws Exception;

}
