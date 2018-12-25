package com.acg12.service;

import com.acg12.entity.po.Acg12Tag;

import java.util.List;
/**
 * SERVICE - Acg12Tag(标签)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12TagService  extends GenericService<Acg12Tag, Long> {
	public	List<Acg12Tag> findListByPage(Object parameter);
	public	List<Acg12Tag> findListNewByPage(Object parameter);
    public	 Long deletes(Object parameter);
}
