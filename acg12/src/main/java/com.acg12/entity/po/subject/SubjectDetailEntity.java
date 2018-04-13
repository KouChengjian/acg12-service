package com.acg12.entity.po.subject;

/**
 * Created by Administrator on 2018/4/4.
 */
public class SubjectDetailEntity {

    private Integer subjectDetailId;
    private Integer subjectId;
    private String otherTitle;
    private String otherValue;

    public Integer getSubjectDetailId() {
        return subjectDetailId;
    }

    public void setSubjectDetailId(Integer subjectDetailId) {
        this.subjectDetailId = subjectDetailId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getOtherTitle() {
        return otherTitle;
    }

    public void setOtherTitle(String otherTitle) {
        this.otherTitle = otherTitle;
    }

    public String getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }
}
