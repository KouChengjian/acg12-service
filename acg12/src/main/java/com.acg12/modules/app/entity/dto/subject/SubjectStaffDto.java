package com.acg12.modules.app.entity.dto.subject;


import com.acg12.modules.app.entity.po.subject.SubjectStaffEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/15.
 */
public class SubjectStaffDto {

    private Integer subjectStaffId;
    private Integer subjectId;
    private Integer sId;
    private Integer personId;
    private Integer pId;
    private String name;
    private String job;
    private List<SubjectStaffDto> subjectStaffDaos = new ArrayList<>();

    public Integer getSubjectStaffId() {
        return subjectStaffId;
    }

    public void setSubjectStaffId(Integer subjectStaffId) {
        this.subjectStaffId = subjectStaffId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public List<SubjectStaffDto> getSubjectStaffDaos() {
        return subjectStaffDaos;
    }

    public void setSubjectStaffDaos(List<SubjectStaffDto> subjectStaffDaos) {
        this.subjectStaffDaos = subjectStaffDaos;
    }

    public void copy(SubjectStaffEntity subjectStaffEntity){
        this.subjectStaffId = subjectStaffEntity.getSubjectStaffId();
        this.subjectId = subjectStaffEntity.getSubjectId();
        this.sId = subjectStaffEntity.getsId();
        this.personId = subjectStaffEntity.getPersonId();
        this.pId = subjectStaffEntity.getpId();
        this.name = subjectStaffEntity.getName();
        this.job = subjectStaffEntity.getJob();
    }
}
