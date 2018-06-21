package com.acg12.modules.app.entity.dto;

import com.acg12.modules.app.entity.po.Tag;

import java.util.List;

public class IndexDto {

    private String url;
    private List<Tag> tagList;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
