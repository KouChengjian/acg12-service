package com.acg12.entity.po;

/**
 * Created by Administrator on 2018/3/14.
 */
public class SubjectEntity extends Param {

    private Integer subject_id;
    private Integer sId;
    private String url;
    private Integer type; // 标题类型
    private String name;
    private String name_cn;
    private String  summary; // 概况
    private String image;
    private Integer eps_count; // 画数
    private String air_date;// 放送开始
    private Integer air_weekday;// 放送时间
    private String otherParam; // 其他参数

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
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

    public String getName_cn() {
        return name_cn;
    }

    public void setName_cn(String name_cn) {
        this.name_cn = name_cn;
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

    public Integer getEps_count() {
        return eps_count;
    }

    public void setEps_count(Integer eps_count) {
        this.eps_count = eps_count;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public Integer getAir_weekday() {
        return air_weekday;
    }

    public void setAir_weekday(Integer air_weekday) {
        this.air_weekday = air_weekday;
    }

    public String getOtherParam() {
        return otherParam;
    }

    public void setOtherParam(String otherParam) {
        this.otherParam = otherParam;
    }
}
