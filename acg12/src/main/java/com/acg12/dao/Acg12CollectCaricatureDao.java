package com.acg12.dao;

import com.acg12.entity.po.Acg12CollectCaricatureEntity;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.util.List;

/**
 * DAO - Acg12CollectCaricatureEntity(CollectCaricature)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12CollectCaricatureDao  extends GenericDao<Acg12CollectCaricatureEntity, Long> {
	List<Acg12CollectCaricatureEntity> findListByPage(Object parameter);
	List<Acg12CollectCaricatureEntity> findListNewByPage(Object parameter);
    Long deletes(Object parameter);
}
