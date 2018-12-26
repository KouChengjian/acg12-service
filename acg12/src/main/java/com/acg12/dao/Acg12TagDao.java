package com.acg12.dao;

import com.acg12.entity.po.Acg12TagEntity;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - Acg12TagEntity(标签)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12TagDao  extends GenericDao<Acg12TagEntity, Long> {
	List<Acg12TagEntity> findListByPage(Object parameter);
	List<Acg12TagEntity> findListNewByPage(Object parameter);
    Long deletes(Object parameter);
}
