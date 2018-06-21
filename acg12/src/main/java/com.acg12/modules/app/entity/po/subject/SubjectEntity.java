package com.acg12.modules.app.entity.po.subject;

import com.acg12.modules.app.entity.po.Param;

/**
 * Created by Administrator on 2018/3/14.
 */
public class SubjectEntity extends Param {

    private Integer subjectId;
    private Integer sId;
    private String url;
    private Integer type; // 类型   1、书籍 2、动画 3、音乐 4、游戏 6、三次元
    private String typeName; // 类型名称
    private String name;
    private String nameCn; // 中文
    private String  summary; // 概况
    private String image;
    private Integer epsCount; // 话数
    private String airDate;// 放送开始 2015-10-10
    private Integer airWeekday;// 放送星期 3
    private String endDate; // 播放结束

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
                ", typeName=" + typeName +
                ", name='" + name + '\'' +
                ", nameCn='" + nameCn + '\'' +
                ", summary='" + summary + '\'' +
                ", image='" + image + '\'' +
                ", epsCount=" + epsCount +
                ", airDate='" + airDate + '\'' +
                ", airWeekday=" + airWeekday +
                ", endDate='" + endDate + '\'' +
                '}';
    }


}