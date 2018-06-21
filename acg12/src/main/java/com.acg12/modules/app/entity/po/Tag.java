package com.acg12.modules.app.entity.po;

/**
 * Created by Administrator on 2017/11/28.
 */
public class Tag {
    private Integer tagId;
    private String tag;
    private String image;
    private Integer createTime;

    public Tag(){
        long time = System.currentTimeMillis()/ 1000;
        setCreateTime(new Long(time).intValue());
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
