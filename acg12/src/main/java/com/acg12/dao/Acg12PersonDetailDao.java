package com.acg12.dao;

import com.acg12.entity.po.Acg12PersonDetailEntity;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.util.List;

/**
 * DAO - Acg12PersonDetail(personDetail)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12PersonDetailDao extends GenericDao<Acg12PersonDetailEntity, Long> {
    List<Acg12PersonDetailEntity> findListByPage(Object parameter);

    List<Acg12PersonDetailEntity> findListNewByPage(Object parameter);

    Long deletes(Object parameter);
}
