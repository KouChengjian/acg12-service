package com.acg12.entity.po;

/**
 * Created by Administrator on 2017/12/1.
 */
public class Video extends Param {

    private Integer vId;
    private Integer bangumitId;
    private String cover;
    private String index;
    private String indexTitle;
    private String danmaku; // 弹幕
    private String bilibiliUrl; // bilibili
    private String baiduyunUrl; // 百度
    private String aiqiyiUrl; // 爱奇艺

    public Integer getvId() {
        return vId;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    public Integer getBangumitId() {
        return bangumitId;
    }

    public void setBangumitId(Integer bangumitId) {
        this.bangumitId = bangumitId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndexTitle() {
        return indexTitle;
    }

    public void setIndexTitle(String indexTitle) {
        this.indexTitle = indexTitle;
    }

    public String getDanmaku() {
        return danmaku;
    }

    public void setDanmaku(String danmaku) {
        this.danmaku = danmaku;
    }

    public String getBilibiliUrl() {
        return bilibiliUrl;
    }

    public void setBilibiliUrl(String bilibiliUrl) {
        this.bilibiliUrl = bilibiliUrl;
    }

    public String getBaiduyunUrl() {
        return baiduyunUrl;
    }

    public void setBaiduyunUrl(String baiduyunUrl) {
        this.baiduyunUrl = baiduyunUrl;
    }

    public String getAiqiyiUrl() {
        return aiqiyiUrl;
    }

    public void setAiqiyiUrl(String aiqiyiUrl) {
        this.aiqiyiUrl = aiqiyiUrl;
    }
}
