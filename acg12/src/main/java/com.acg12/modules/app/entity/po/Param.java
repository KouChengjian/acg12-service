package com.acg12.modules.app.entity.po;


import java.io.Serializable;

public class Param implements Serializable {
	private static final long serialVersionUID = -7180816291844644837L;

	private Integer createTime;
	private Integer updateTime;

	public Param(){
		long time = System.currentTimeMillis()/ 1000;
		setCreateTime(new Long(time).intValue());
		setUpdateTime(new Long(time).intValue());
	}

	public void updateTime(){
		long time = System.currentTimeMillis()/ 1000;
		setUpdateTime(new Long(time).intValue());
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public Integer getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
}
