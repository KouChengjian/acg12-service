package com.acg12.dao;

import com.acg12.entity.po.Acg12SubjectDetailEntity;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.util.List;

/**
 * DAO - Acg12SubjectDetail(subjectDetail)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12SubjectDetailDao extends GenericDao<Acg12SubjectDetailEntity, Long> {
    List<Acg12SubjectDetailEntity> findListByPage(Object parameter);

    List<Acg12SubjectDetailEntity> findListNewByPage(Object parameter);

    Long deletes(Object parameter);
}
