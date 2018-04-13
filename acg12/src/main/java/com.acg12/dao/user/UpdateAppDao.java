package com.acg12.dao.user;

import com.acg12.entity.po.user.Update;

/**
 * Created by Administrator on 2017/6/21.
 */
public interface UpdateAppDao {

    int insert(Update update);

    Update queryUpdate(int id);
}
