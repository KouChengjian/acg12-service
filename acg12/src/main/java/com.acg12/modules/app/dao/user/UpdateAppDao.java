package com.acg12.modules.app.dao.user;


import com.acg12.modules.app.entity.po.user.Update;

/**
 * Created by Administrator on 2017/6/21.
 */
public interface UpdateAppDao {

    int insert(Update update);

    Update queryUpdate(int id);
}
