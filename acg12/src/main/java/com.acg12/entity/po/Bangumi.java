package com.acg12.entity.po;

/**
 * Created by Administrator on 2017/11/14.
 */
public class Bangumi extends Param {

    private Integer bangumitId;
    private String platform; // 来自平台
    private String title;
    private String cover;
    private String tags;
    private String intro; // 简介
    private String voiceActor; // 声优
    private String nowStatus; // 现在状态 1 连载 2 完结
    private String totalCount; // 总集数
    private String serializeWeek; // 连载更新周
    private Integer serializeTime; // 连载更新时间
    private Integer startPlayTime; // 开始播放时间


    public Bangumi(){
    }

    public Integer getBangumitId() {
        return bangumitId;
    }

    public void setBangumitId(Integer bangumitId) {
        this.bangumitId = bangumitId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getNowStatus() {
        return nowStatus;
    }

    public void setNowStatus(String nowStatus) {
        this.nowStatus = nowStatus;
    }

    public String getVoiceActor() {
        return voiceActor;
    }

    public void setVoiceActor(String voiceActor) {
        this.voiceActor = voiceActor;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getSerializeWeek() {
        return serializeWeek;
    }

    public void setSerializeWeek(String serializeWeek) {
        this.serializeWeek = serializeWeek;
    }

    public Integer getSerializeTime() {
        return serializeTime;
    }

    public void setSerializeTime(Integer serializeTime) {
        this.serializeTime = serializeTime;
    }

    public Integer getStartPlayTime() {
        return startPlayTime;
    }

    public void setStartPlayTime(Integer startPlayTime) {
        this.startPlayTime = startPlayTime;
    }

    @Override
    public String toString() {
        return "Bangumi{" +
                "bangumitId=" + bangumitId +
                ", platform='" + platform + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", tags='" + tags + '\'' +
                ", intro='" + intro + '\'' +
                ", voiceActor='" + voiceActor + '\'' +
                ", nowStatus='" + nowStatus + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", serializeWeek='" + serializeWeek + '\'' +
                ", serializeTime=" + serializeTime + '\'' +
                ", startPlayTime=" + startPlayTime +
                '}';
    }
}
