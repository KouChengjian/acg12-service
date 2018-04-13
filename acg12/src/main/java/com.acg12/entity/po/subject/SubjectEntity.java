package com.acg12.entity.po.subject;

import com.acg12.entity.po.Param;

/**
 * Created by Administrator on 2018/3/14.
 */
public class SubjectEntity extends Param {

    private Integer subjectId;
    private Integer sId;
    private String url;
    private Integer type; // 标题类型
    private String name;
    private String nameCn; // 中文
    private String  summary; // 概况
    private String image;
    private Integer epsCount; // 话数
    private String airDate;// 放送开始
    private Integer airWeekday;// 放送星期

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getEpsCount() {
        return epsCount;
    }

    public void setEpsCount(Integer epsCount) {
        this.epsCount = epsCount;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public Integer getAirWeekday() {
        return airWeekday;
    }

    public void setAirWeekday(Integer airWeekday) {
        this.airWeekday = airWeekday;
    }

    @Override
    public boolean equals(Object obj) {
        if(toString().hashCode() == obj.toString().hashCode()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "SubjectEntity{" +
                "sId=" + sId +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", nameCn='" + nameCn + '\'' +
                ", summary='" + summary + '\'' +
                ", image='" + image + '\'' +
                ", epsCount=" + epsCount +
                ", airDate='" + airDate + '\'' +
                ", airWeekday=" + airWeekday +
                '}';
    }
}
