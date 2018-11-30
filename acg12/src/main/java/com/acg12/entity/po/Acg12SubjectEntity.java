package com.acg12.entity.po;


import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Created by Administrator on 2018/3/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_SUBJECT")
public class Acg12SubjectEntity implements GenericEntity {

    private static final long serialVersionUID = 5081846432919091193L;

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

    /** 锁定状态  1正常 2锁定 */
    @Column(name = "lock_status" )
    private Integer lockStatus;

    /** 创建时间 */
    @Column(name = "create_time" )
    private java.util.Date createTime;

    /** 修改时间 */
    @Column(name = "update_time" )
    private java.util.Date updateTime;

    @Override
    public boolean equals(Object obj) {
        if(toString().hashCode() == obj.toString().hashCode()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Acg12SubjectEntity{" +
                "sId=" + sId +
                ", type=" + type +
                ", typeName=" + typeName +
                ", name='" + name + '\'' +
                ", nameCn='" + nameCn + '\'' +
                ", epsCount=" + epsCount +
                ", airDate='" + airDate + '\'' +
                ", airWeekday=" + airWeekday +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
