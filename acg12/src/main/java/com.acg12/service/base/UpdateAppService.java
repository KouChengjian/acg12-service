package com.acg12.service.base;

import com.acg12.entity.po.user.Update;

/**
 * Created by Administrator on 2017/6/21.
 */
public interface UpdateAppService {

    int save(Update update) throws Exception;

    Update queryUpdate(int id) throws Exception;
}
