package com.acg12.service.impl;

import com.acg12.dao.Acg12SubjectOffprintDao;
import com.acg12.entity.po.Acg12SubjectOffprintEntity;
import com.acg12.service.Acg12SubjectOffprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SERVICE - Acg12SubjectOffprint(subjectOffprint)
 *
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12SubjectOffprintServiceImpl extends GenericServiceImpl<Acg12SubjectOffprintEntity, Long> implements Acg12SubjectOffprintService {

    @Autowired
    private Acg12SubjectOffprintDao acg12SubjectOffprintDao;


    @Autowired
    public void setGenericDao() {
        super.setGenericDao(acg12SubjectOffprintDao);
    }

    public List<Acg12SubjectOffprintEntity> findListByPage(Object parameter) {
        return acg12SubjectOffprintDao.findListByPage(parameter);
    }

    public List<Acg12SubjectOffprintEntity> findListNewByPage(Object parameter) {
        return acg12SubjectOffprintDao.findListNewByPage(parameter);
    }

    public Long deletes(Object parameter) {
        return acg12SubjectOffprintDao.deletes(parameter);
    }


}
