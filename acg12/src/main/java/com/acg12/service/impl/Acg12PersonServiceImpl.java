package com.acg12.service.impl;

import com.acg12.dao.Acg12PersonDao;
import com.acg12.entity.po.Acg12PersonEntity;
import com.acg12.service.Acg12PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SERVICE - Acg12Person(person)
 *
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12PersonServiceImpl extends GenericServiceImpl<Acg12PersonEntity, Long> implements Acg12PersonService {

    @Autowired
    private Acg12PersonDao acg12PersonDao;


    @Autowired
    public void setGenericDao() {
        super.setGenericDao(acg12PersonDao);
    }

    public List<Acg12PersonEntity> findListByPage(Object parameter) {
        return acg12PersonDao.findListByPage(parameter);
    }

    public List<Acg12PersonEntity> findListNewByPage(Object parameter) {
        return acg12PersonDao.findListNewByPage(parameter);
    }

    public Long deletes(Object parameter) {
        return acg12PersonDao.deletes(parameter);
    }


}
