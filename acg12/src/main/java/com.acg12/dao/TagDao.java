package com.acg12.dao;

import com.acg12.entity.po.Tag;

import java.util.List;

/**
 * Created by Administrator on 2017/11/28.
 */
public interface TagDao {

    int insert(Tag tag);

    List<Tag> queryList();

}
