package com.acg12.entity.dto.subject;

import com.acg12.entity.po.subject.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/15.
 */
public class SubjectInfoDto {

    private Integer subjectId;
    private Integer sId;
    private String url;
    private Integer type; // 类型   1、书籍 2、动画 3、音乐 4、游戏 6、三次元
    private String typeName; // 类型名称
    private String name;
    private String nameCn; // 中文
    private String summary; // 概况
    private String image;
    private Integer epsCount; // 话数
    private String airDate;// 放送开始 2015-10-10
    private Integer airWeekday;// 放送星期 3
    private String endDate; // 播放结束

    // 额外
    private String author;

    private List<SubjectDetailEntity> details = new ArrayList<>();
    private List<SubjectStaffEntity> staff = new ArrayList<>();
    private List<SubjectCrtEntity> crt = new ArrayList<>();
    private List<SubjectOffprintEntity> offprint = new ArrayList<>();
    private List<SubjectSongEntity> song = new ArrayList<>();

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


    public void copy(SubjectEntity subjectEntity) {
        if (subjectEntity == null) {
            return;
        }
        this.subjectId = subjectEntity.getSubjectId();
        this.sId = subjectEntity.getsId();
        this.url = subjectEntity.getUrl();
        this.type = subjectEntity.getType(); // 类型   1、书籍 2、动画 3、音乐 4、游戏 6、三次元
        this.typeName = subjectEntity.getTypeName(); // 类型名称
        this.name = subjectEntity.getName();
        this.nameCn = subjectEntity.getNameCn(); // 中文
        this.summary = subjectEntity.getSummary(); // 概况
        this.image = subjectEntity.getImage();
        this.epsCount = subjectEntity.getEpsCount(); // 话数
        this.airDate = subjectEntity.getAirDate();// 放送开始 2015-10-10
        this.airWeekday = subjectEntity.getAirWeekday();// 放送星期 3
        this.endDate = subjectEntity.getEndDate(); // 播放结束
    }

    public List<SubjectDetailEntity> getDetails() {
        return details;
    }

    public void setDetails(List<SubjectDetailEntity> details) {
        this.details = details;
    }

    public List<SubjectStaffEntity> getStaff() {
        return staff;
    }

    public void setStaff(List<SubjectStaffEntity> staff) {
        this.staff = staff;
    }

    public List<SubjectCrtEntity> getCrt() {
        return crt;
    }

    public void setCrt(List<SubjectCrtEntity> crt) {
        this.crt = crt;
    }

    public List<SubjectOffprintEntity> getOffprint() {
        return offprint;
    }

    public void setOffprint(List<SubjectOffprintEntity> offprint) {
        this.offprint = offprint;
    }

    public List<SubjectSongEntity> getSong() {
        return song;
    }

    public void setSong(List<SubjectSongEntity> song) {
        this.song = song;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
