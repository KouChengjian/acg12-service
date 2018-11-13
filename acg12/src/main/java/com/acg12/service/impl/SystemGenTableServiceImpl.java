package com.acg12.service.impl;

import com.acg12.dao.SystemGenTableDao;
import com.acg12.entity.po.SystemGenTableEntity;
import com.acg12.service.SystemGenTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - 表
 *
 * @author Mounate Yan。
 * @version 1.0
 */
@Service("genTableServiceImpl")
public class SystemGenTableServiceImpl extends GenericServiceImpl<SystemGenTableEntity, Long> implements SystemGenTableService {

    @Autowired
    private SystemGenTableDao genTableDao;

    @Autowired
    public void setGenericDao() {
        super.setGenericDao(genTableDao);
    }
}