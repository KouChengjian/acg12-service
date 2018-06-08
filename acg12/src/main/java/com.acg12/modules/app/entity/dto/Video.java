package com.acg12.modules.app.entity.dto;

import java.util.List;

public class Video {

	private String aid;// 视频av号
	private String typeid;// 视频类型
	private String title;// 视频标题
	private String sbutitle;
	private String play;// 视频播放数
	private String review;// 评论数
	private String videoReview;// 视频弹幕数
	private String favorites;// 视频收藏数
	private String mid; // 弹幕id
	private String author;// Up主
	private String description;// 视频简介
	private String create;// 视频发布时间
	private String pic;// 视频封面地址
	private String credit; // 创建时间
	private String coins;// 视频硬币数
	private String duration;// 视频长度
	// 后续添加的字段
	private String bmId; // 番剧id
	private String urlInfo;// 链接详细信息
	private String updateContent;// 更新信息

	private String voiceActor; // 声优

	private List<Video> bangumiVideoList; // 番剧列表
	private List<Video> quarterVideoList; // 季度视频

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSbutitle() {
		return sbutitle;
	}

	public void setSbutitle(String sbutitle) {
		this.sbutitle = sbutitle;
	}

	public String getPlay() {
		return play;
	}

	public void setPlay(String play) {
		this.play = play;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getVideoReview() {
		return videoReview;
	}

	public void setVideoReview(String videoReview) {
		this.videoReview = videoReview;
	}

	public String getFavorites() {
		return favorites;
	}

	public void setFavorites(String favorites) {
		this.favorites = favorites;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreate() {
		return create;
	}

	public void setCreate(String create) {
		this.create = create;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getCoins() {
		return coins;
	}

	public void setCoins(String coins) {
		this.coins = coins;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getUrlInfo() {
		return urlInfo;
	}

	public void setUrlInfo(String urlInfo) {
		this.urlInfo = urlInfo;
	}

	public String getUpdateContent() {
		return updateContent;
	}

	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	public List<Video> getBangumiVideoList() {
		return bangumiVideoList;
	}

	public void setBangumiVideoList(List<Video> bangumiVideoList) {
		this.bangumiVideoList = bangumiVideoList;
	}

	public List<Video> getQuarterVideoList() {
		return quarterVideoList;
	}

	public void setQuarterVideoList(List<Video> quarterVideoList) {
		this.quarterVideoList = quarterVideoList;
	}

	public String getBmId() {
		return bmId;
	}

	public void setBmId(String bmId) {
		this.bmId = bmId;
	}


	@Override
	public String toString() {
		return "Video{" +
				"aid='" + aid + '\'' +
				", typeid='" + typeid + '\'' +
				", title='" + title + '\'' +
				", sbutitle='" + sbutitle + '\'' +
				", play='" + play + '\'' +
				", review='" + review + '\'' +
				", videoReview='" + videoReview + '\'' +
				", favorites='" + favorites + '\'' +
				", mid='" + mid + '\'' +
				", author='" + author + '\'' +
				", description='" + description + '\'' +
				", create='" + create + '\'' +
				", pic='" + pic + '\'' +
				", credit='" + credit + '\'' +
				", coins='" + coins + '\'' +
				", duration='" + duration + '\'' +
				", bmId='" + bmId + '\'' +
				", urlInfo='" + urlInfo + '\'' +
				", updateContent='" + updateContent + '\'' +
				", bangumiVideoList=" + bangumiVideoList +
				", quarterVideoList=" + quarterVideoList +
				'}';
	}

	public String getVoiceActor() {
		return voiceActor;
	}

	public void setVoiceActor(String voiceActor) {
		this.voiceActor = voiceActor;
	}
}
