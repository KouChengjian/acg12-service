package com.acg12.service;

import com.acg12.entity.po.Acg12TagEntity;

import java.util.List;
/**
 * SERVICE - Acg12TagEntity(标签)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12TagService  extends GenericService<Acg12TagEntity, Long> {
	public	List<Acg12TagEntity> findListByPage(Object parameter);
	public	List<Acg12TagEntity> findListNewByPage(Object parameter);
    public	 Long deletes(Object parameter);
}
