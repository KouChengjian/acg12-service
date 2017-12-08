package com.acg12.dao;

import com.acg12.entity.po.Update;

/**
 * Created by Administrator on 2017/6/21.
 */
public interface UpdateAppDao {

    int insert(Update update);

    Update queryUpdate(int id);
}
