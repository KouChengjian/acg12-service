package com.acg12.modules.app.entity.po.person;

/**
 * Created by Administrator on 2018/4/8.
 */
public class PersonDetailEntity {

    private Integer personDetailId;
    private Integer personId;
    private String summary;
    private String otherTitle;
    private String otherValue;

    public Integer getPersonDetailId() {
        return personDetailId;
    }

    public void setPersonDetailId(Integer personDetailId) {
        this.personDetailId = personDetailId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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