package com.acg12.entity.po;

import java.util.ArrayList;

public class Album {

	private String pinId = "";
	private String content; // 内容
	private String image;
	private Integer resWidth; // 资源宽度
	private Integer resHight; // 资源高度
	private Integer love; // 喜欢的个数  点赞
	private Integer favorites;// 收藏的个数 采集

	public String getPinId() {
		return pinId;
	}

	public void setPinId(String pinId) {
		this.pinId = pinId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getResWidth() {
		return resWidth;
	}

	public void setResWidth(Integer resWidth) {
		this.resWidth = resWidth;
	}

	public Integer getResHight() {
		return resHight;
	}

	public void setResHight(Integer resHight) {
		this.resHight = resHight;
	}

	public Integer getLove() {
		return love;
	}

	public void setLove(Integer love) {
		this.love = love;
	}

	public Integer getFavorites() {
		return favorites;
	}

	public void setFavorites(Integer favorites) {
		this.favorites = favorites;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
