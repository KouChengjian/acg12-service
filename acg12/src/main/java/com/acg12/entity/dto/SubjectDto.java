package com.acg12.entity.dto;

import com.acg12.entity.po.*;
import com.alibaba.fastjson.JSON;
import com.framework.loippi.mybatis.ext.annotation.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
public class SubjectDto {

    /**  */
    @Column(id = true, name = "id", updatable = false)
    private Integer id;

    /**  */
    @Column(name = "s_id" )
    private Integer sId;

    /** 1、书籍 2、动画 3、音乐 4、游戏 6、三次元 */
    @Column(name = "type" )
    private Integer type;

    /** 类型名称 */
    @Column(name = "type_name" )
    private String typeName;

    /** 名称 */
    @Column(name = "name" )
    private String name;

    /** name_cn */
    @Column(name = "name_cn" )
    private String nameCn;

    /** 概况 */
    @Column(name = "summary" )
    private String summary;

    /** 封面 */
    @Column(name = "image" )
    private String image;

    /** 话数 */
    @Column(name = "eps_count" )
    private Integer epsCount;

    /** 放送开始 */
    @Column(name = "air_date" )
    private String airDate;

    /** 放送星期 */
    @Column(name = "air_weekday" )
    private Integer airWeekday;

    /** 播放结束 */
    @Column(name = "end_date" )
    private String endDate;


    private Acg12SubjectEntity info;

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


    @Override
    public String toString() {
        return JSON.toJSON(this).toString();
    }
}
