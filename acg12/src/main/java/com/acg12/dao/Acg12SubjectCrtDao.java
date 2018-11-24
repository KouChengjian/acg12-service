package com.acg12.dao;

import com.acg12.entity.po.Acg12SubjectCrtEntity;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.util.List;

/**
 * DAO - Acg12SubjectCrt(subjectCrt)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12SubjectCrtDao  extends GenericDao<Acg12SubjectCrtEntity, Long> {
	List<Acg12SubjectCrtEntity> findListByPage(Object parameter);
	List<Acg12SubjectCrtEntity> findListNewByPage(Object parameter);
    Long deletes(Object parameter);
}
