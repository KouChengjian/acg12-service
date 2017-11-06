package com.acg12.service;

import com.acg12.entity.po.Update;
import com.acg12.dao.UpdateAppMapper;
import com.acg12.service.base.UpdateAppService;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/6/21.
 */
public class UpdateAppServiceImpl implements UpdateAppService {

    @Resource
    private UpdateAppMapper updateAppMapper;

    @Override
    public int save(Update update) throws Exception {
        return updateAppMapper.insert(update);
    }

    @Override
    public Update queryUpdate(int id) throws Exception {
        return updateAppMapper.queryUpdate(id);
    }

}
