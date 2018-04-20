package com.acg12.service.subject.impl;

import com.acg12.dao.subject.SubjectCrtDao;
import com.acg12.dao.subject.SubjectDao;
import com.acg12.dao.subject.SubjectDetailDao;
import com.acg12.dao.subject.SubjectStaffDao;
import com.acg12.entity.po.subject.SubjectCrtEntity;
import com.acg12.entity.po.subject.SubjectDetailEntity;
import com.acg12.entity.po.subject.SubjectEntity;
import com.acg12.entity.po.subject.SubjectStaffEntity;
import com.acg12.service.subject.SubjectService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Resource;
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

    @Override
    public JSONObject getSubjectInfo(int subjectId) throws Exception{
        SubjectEntity subjectEntity = subjectDao.queryBySubjectId(2);
        List<SubjectDetailEntity> subjectDetailEntityList = subjectDetailsDao.queryBySubjectId(subjectEntity.getSubjectId());
        List<SubjectStaffEntity> subjectStaffEntityList = subjectStaffDao.queryBySubjectId(subjectEntity.getSubjectId());
        List<SubjectCrtEntity> subjectCrtEntityList = subjectCrtDao.queryBySubjectId(subjectEntity.getSubjectId());
        if(subjectEntity == null){
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
        return  subjectJsonObject;
    }
}
