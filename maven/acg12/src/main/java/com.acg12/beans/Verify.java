package com.acg12.beans;

/**
 * Created by Administrator on 2017/5/31.
 */
public class Verify {

    private Integer id;
    private Integer uid;
    private Integer verifycode;
    private Integer duration; // 有效时间
    private Integer status; // 状态 0 未使用 1 已使用 2 已过期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(Integer verifycode) {
        this.verifycode = verifycode;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
