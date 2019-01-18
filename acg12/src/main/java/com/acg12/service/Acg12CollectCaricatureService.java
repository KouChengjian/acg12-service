package com.acg12.service;

import com.acg12.entity.po.Acg12CollectCaricatureEntity;

import java.util.List;
/**
 * SERVICE - Acg12CollectCaricatureEntity(CollectCaricature)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12CollectCaricatureService  extends GenericService<Acg12CollectCaricatureEntity, Long> {
	public	List<Acg12CollectCaricatureEntity> findListByPage(Object parameter);
	public	List<Acg12CollectCaricatureEntity> findListNewByPage(Object parameter);
    public	 Long deletes(Object parameter);
}
