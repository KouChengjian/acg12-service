package com.acg12.service.impl;

import com.acg12.dao.Acg12SubjectDao;
import com.acg12.entity.po.Acg12SubjectEntity;
import com.acg12.service.Acg12SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SERVICE - Acg12SubjectEntity(subject)
 *
 * @author wmj
 * @version 2.0
 */
@Service("acg12SubjectServiceImpl")
public class Acg12SubjectServiceImpl extends GenericServiceImpl<Acg12SubjectEntity, Long> implements Acg12SubjectService {

    @Autowired
    private Acg12SubjectDao acg12SubjectEntityDao;


    @Autowired
    public void setGenericDao() {
        super.setGenericDao(acg12SubjectEntityDao);
    }

    public List<Acg12SubjectEntity> findListByPage(Object parameter) {
        return acg12SubjectEntityDao.findListByPage(parameter);
    }

    public List<Acg12SubjectEntity> findListNewByPage(Object parameter) {
        return acg12SubjectEntityDao.findListNewByPage(parameter);
    }

    public Long deletes(Object parameter) {
        return acg12SubjectEntityDao.deletes(parameter);
    }


}
