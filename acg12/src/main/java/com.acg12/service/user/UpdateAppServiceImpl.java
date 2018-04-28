package com.acg12.service.user;

import com.acg12.entity.po.user.Update;
import com.acg12.dao.user.UpdateAppDao;
import com.acg12.service.base.UpdateAppService;

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
