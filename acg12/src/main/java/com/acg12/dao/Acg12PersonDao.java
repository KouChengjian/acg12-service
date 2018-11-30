package com.acg12.dao;

import com.acg12.entity.po.Acg12PersonEntity;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.util.List;

/**
 * DAO - Acg12Person(person)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12PersonDao extends GenericDao<Acg12PersonEntity, Long> {
    List<Acg12PersonEntity> findListByPage(Object parameter);

    List<Acg12PersonEntity> findListNewByPage(Object parameter);

    Long deletes(Object parameter);
}
