package com.acg12.modules.app.service.impl;

import com.acg12.modules.app.dao.user.UpdateAppDao;
import com.acg12.modules.app.entity.po.user.Update;
import com.acg12.modules.app.service.UpdateAppService;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/21.
 */
public class UpdateAppServiceImpl implements UpdateAppService {

    @Resource
    private UpdateAppDao updateAppMapper;

    @Override
    public int save(Update update) throws Exception {
        return updateAppMapper.insert(update);
    }

    @Override
    public Update queryUpdate(int id) throws Exception {
        return updateAppMapper.queryUpdate(id);
    }

}
