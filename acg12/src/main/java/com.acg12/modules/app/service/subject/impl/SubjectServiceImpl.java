package com.acg12.modules.app.service.subject.impl;

import com.acg12.common.pagination.PageInfo;
import com.acg12.modules.app.dao.subject.*;
import com.acg12.modules.app.entity.dto.subject.SubjectInfoDto;
import com.acg12.modules.app.entity.po.subject.*;
import com.acg12.modules.app.service.subject.SubjectService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */
public class SubjectServiceImpl implements SubjectService {

    @Resource
    private SubjectDao subjectDao;
    @Resource
    private SubjectDetailDao subjectDetailsDao;
    @Resource
    private SubjectStaffDao subjectStaffDao;
    @Resource
    private SubjectCrtDao subjectCrtDao;
    @Resource
    private SubjectOffprintDao subjectOffprintDao;
    @Resource
    private SubjectSongDao subjectSongDao;

    @Override
    public List<SubjectEntity> queryBySubjectList(PageInfo pageInfo, String type, String typeName, String year, String month, String status) {
        return subjectDao.queryBySubjectListPage(pageInfo, type, typeName, year, month, status);
    }

    @Override
    public List<SubjectEntity> queryBySubjectList(PageInfo pageInfo, String type, String typeName, String platform, String year) {
        return subjectDao.queryBySubjectGameListPage(pageInfo, type, typeName, platform, year);
    }

    @Override
    public SubjectInfoDto queryBySIdJoinDetail(int sId) {
        SubjectInfoDto subjectInfoDto = new SubjectInfoDto();
        SubjectEntity subjectEntity = subjectDao.queryBySId(sId);
        if (subjectEntity == null) {
            return subjectInfoDto;
        }
        List<SubjectDetailEntity> subjectDetailEntityList = subjectDetailsDao.queryBySId(subjectEntity.getsId());
        List<SubjectStaffEntity> subjectStaffEntityList = subjectStaffDao.queryBySId(subjectEntity.getsId());
        List<SubjectCrtEntity> subjectCrtEntityList = subjectCrtDao.queryBySId(subjectEntity.getsId());

        subjectInfoDto.copy(subjectEntity);
        subjectInfoDto.setDetails(subjectDetailEntityList);
        subjectInfoDto.setStaff(subjectStaffEntityList);
        subjectInfoDto.setCrt(subjectCrtEntityList);
        Iterator<SubjectStaffEntity> iterator = subjectStaffEntityList.iterator();
        while (iterator.hasNext()) {
            SubjectStaffEntity subjectStaffEntity = iterator.next();
            if (subjectStaffEntity.getJob().equals("作者") || subjectStaffEntity.getJob().equals("原作")) {
                subjectInfoDto.setAuthor(subjectStaffEntity.getName());
                iterator.remove();
                continue;
            }
        }

        if (subjectEntity.getType() == 1) {
            List<SubjectOffprintEntity> subjectOffprintEntityList = subjectOffprintDao.queryBySId(subjectEntity.getsId());
            subjectInfoDto.setOffprint(subjectOffprintEntityList);
        } else if (subjectEntity.getType() == 3) {
            List<SubjectSongEntity> subjectSongEntityList = subjectSongDao.queryBySId(subjectEntity.getsId());
            subjectInfoDto.setSong(subjectSongEntityList);
        }
        return subjectInfoDto;
    }

    @Override
    public JSONObject getSubjectInfo(int subjectId) throws Exception {
        SubjectEntity subjectEntity = subjectDao.queryBySubjectId(2);
        List<SubjectDetailEntity> subjectDetailEntityList = subjectDetailsDao.queryBySubjectId(subjectEntity.getSubjectId());
        List<SubjectStaffEntity> subjectStaffEntityList = subjectStaffDao.queryBySubjectId(subjectEntity.getSubjectId());
        List<SubjectCrtEntity> subjectCrtEntityList = subjectCrtDao.queryBySubjectId(subjectEntity.getSubjectId());
        if (subjectEntity == null) {
            return null;
        }
        JSONObject subjectJsonObject = new JSONObject(new Gson().toJson(subjectEntity));
        JSONArray subjectDetailJsonObject = new JSONArray(new Gson().toJson(subjectDetailEntityList));
        JSONArray subjectStaffJsonObject = new JSONArray(new Gson().toJson(subjectStaffEntityList));
        JSONArray subjectCrtJsonObject = new JSONArray(new Gson().toJson(subjectCrtEntityList));

        subjectJsonObject.put("details", subjectDetailJsonObject);
        subjectJsonObject.put("staff", subjectStaffJsonObject);
        subjectJsonObject.put("crt", subjectCrtJsonObject);
        System.err.println(subjectJsonObject.toString());
        return subjectJsonObject;
    }
}
