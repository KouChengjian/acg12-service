package com.acg12.dao;

import com.acg12.entity.po.Acg12SubjectOffprintEntity;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.util.List;

/**
 * DAO - Acg12SubjectOffprint(subjectOffprint)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12SubjectOffprintDao extends GenericDao<Acg12SubjectOffprintEntity, Long> {
    List<Acg12SubjectOffprintEntity> findListByPage(Object parameter);

    List<Acg12SubjectOffprintEntity> findListNewByPage(Object parameter);

    Long deletes(Object parameter);
}
