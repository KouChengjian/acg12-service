package com.acg12.modules.app.dao;


import com.acg12.modules.app.entity.po.Tag;

import java.util.List;

/**
 * Created by Administrator on 2017/11/28.
 */
public interface TagDao {

    int insert(Tag tag);

    List<Tag> queryList();

}
