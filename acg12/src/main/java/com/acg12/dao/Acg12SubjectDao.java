package com.acg12.dao;

import com.acg12.entity.po.Acg12SubjectEntity;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.util.List;

/**
 * DAO - Acg12SubjectEntity(subject)
 *
 * @author wmj
 * @version 2.0
 */
public interface Acg12SubjectDao extends GenericDao<Acg12SubjectEntity, Long> {
    List<Acg12SubjectEntity> findListByPage(Object parameter);

    List<Acg12SubjectEntity> findListNewByPage(Object parameter);

    Long deletes(Object parameter);
}
