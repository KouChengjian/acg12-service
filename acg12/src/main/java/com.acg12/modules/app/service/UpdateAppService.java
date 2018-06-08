package com.acg12.modules.app.service;


import com.acg12.modules.app.entity.po.user.Update;

/**
 * Created by Administrator on 2017/6/21.
 */
public interface UpdateAppService {

    int save(Update update) throws Exception;

    Update queryUpdate(int id) throws Exception;
}
