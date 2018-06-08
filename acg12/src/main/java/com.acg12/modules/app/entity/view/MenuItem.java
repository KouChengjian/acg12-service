package com.acg12.modules.app.entity.view;

import java.awt.*;

/**
 * Created by Administrator on 2018/5/17.
 */
public class MenuItem {

    private String title;
    private String url;

    public MenuItem(String title , String url){
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
