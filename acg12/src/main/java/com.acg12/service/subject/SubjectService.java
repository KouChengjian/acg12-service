package com.acg12.service.subject;

import com.acg12.entity.dto.subject.SubjectInfoDto;
import com.acg12.entity.po.subject.SubjectEntity;
import com.acg12.utils.pagination.PageInfo;
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
