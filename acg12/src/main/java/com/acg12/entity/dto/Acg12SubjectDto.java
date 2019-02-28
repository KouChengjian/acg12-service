package com.acg12.entity.dto;

import com.acg12.entity.po.*;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/28 17:09
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Acg12SubjectDto {

    /**  */
    private Integer id;

    /**  */
    private Integer sId;

    /** 1、书籍 2、动画 3、音乐 4、游戏 6、三次元 */
    private Integer type;

    /** 类型名称 */
    private String typeName;

    /** 名称 */
    private String name;

    /** name_cn */
    private String nameCn;

    /** 概况 */
    private String summary;

    /** 封面 */
    private String image;

    /** 话数 */
    private Integer epsCount;

    /** 放送开始 */
    private String airDate;

    /** 放送星期 */
    private Integer airWeekday;

    /** 播放结束 */
    private String endDate;

    /** 是否收藏 */
    private Integer isCollect;

    private List<Acg12SubjectDetailEntity> detailList = new ArrayList<>();
    private List<Acg12SubjectStaffEntity> staffList = new ArrayList<>();
    private List<Acg12SubjectCrtEntity> crtList = new ArrayList<>();
    private List<Acg12SubjectOffprintEntity> offprintList = new ArrayList<>();
    private List<Acg12SubjectSongEntity> songList = new ArrayList<>();

    public void copy(Acg12SubjectEntity subjectEntity) {
        if (subjectEntity == null) {
            return;
        }
        this.id = subjectEntity.getId();
        this.sId = subjectEntity.getSId();
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

    public Acg12SubjectEntity chanage() {
        Acg12SubjectEntity subjectEntity = new Acg12SubjectEntity();

        subjectEntity.setId(this.id);
        subjectEntity.setSId(this.sId);
        subjectEntity.setType(this.type); // 类型   1、书籍 2、动画 3、音乐 4、游戏 6、三次元
        subjectEntity.setTypeName(this.typeName); // 类型名称
        subjectEntity.setName(this.name);
        subjectEntity.setNameCn(this.nameCn); // 中文
        subjectEntity.setSummary(this.summary); // 概况
        subjectEntity.setImage(this.image);
        subjectEntity.setEpsCount(this.epsCount); // 话数
        subjectEntity.setAirDate(this.airDate);// 放送开始 2015-10-10
        subjectEntity.setAirWeekday(this.airWeekday);// 放送星期 3
        subjectEntity.setEndDate(this.endDate); // 播放结束
        subjectEntity.setCreateTime(new Date());
        subjectEntity.setUpdateTime(new Date());
        return subjectEntity;
    }


    @Override
    public String toString() {
        return JSON.toJSON(this).toString();
    }
}
