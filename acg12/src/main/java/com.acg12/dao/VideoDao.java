package com.acg12.dao;

import com.acg12.entity.po.Video;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1.
 */
public interface VideoDao {

    int insert(Video bangumi);

    List<Video> queryList();
}
