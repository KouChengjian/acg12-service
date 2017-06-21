package com.acg12.mapper;

import com.acg12.beans.Update;

/**
 * Created by Administrator on 2017/6/21.
 */
public interface UpdateAppMapper {

    int insert(Update update);

    Update queryUpdate(int id);
}
