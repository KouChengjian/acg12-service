package com.acg12.entity.po.person;

/**
 * Created by Administrator on 2018/4/8.
 */
public class PersonDetailEntity {
    private Integer personDetailId;
    private Integer personId;
    private String job;
    private String alias;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
