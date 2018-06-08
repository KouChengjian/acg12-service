package com.acg12.modules.app.entity.po.user;

import com.acg12.modules.app.entity.po.Param;

/**
 * Created by Administrator on 2017/5/31.
 */
public class Verify extends Param {

    private Integer id;
    private String phone;
    private Integer verifycode;
    private Integer duration; // 有效时间
    private Integer status; // 状态 0 未使用 1 已使用 2 已过期
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
