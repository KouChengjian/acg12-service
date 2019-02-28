package com.acg12.dao;

import com.acg12.entity.po.Acg12CollectSubjectEntity;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.util.List;

/**
 * DAO - Acg12CollectSubjectEntity(collectSubject)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12CollectSubjectDao  extends GenericDao<Acg12CollectSubjectEntity, Long> {
	List<Acg12CollectSubjectEntity> findListByPage(Object parameter);
	List<Acg12CollectSubjectEntity> findListNewByPage(Object parameter);
    Long deletes(Object parameter);
}
