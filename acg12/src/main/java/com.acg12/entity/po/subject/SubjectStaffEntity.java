package com.acg12.entity.po.subject;

import com.acg12.entity.po.Param;

/**
 * Created by Administrator on 2018/4/11.
 */
public class SubjectStaffEntity extends Param{

    private Integer subjectStaffId;
    private Integer subjectId;
    private Integer personId;
    private String name;
    private String nameCn;
    private String image;
    private String job;
    private String gender;

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

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
