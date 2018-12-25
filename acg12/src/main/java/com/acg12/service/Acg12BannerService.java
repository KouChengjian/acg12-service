package com.acg12.service;

import com.acg12.entity.po.Acg12BannerEntity;

import java.util.List;
/**
 * SERVICE - Acg12BannerEntity(banner)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12BannerService  extends GenericService<Acg12BannerEntity, Long> {
	public	List<Acg12BannerEntity> findListByPage(Object parameter);
	public	List<Acg12BannerEntity> findListNewByPage(Object parameter);
    public	 Long deletes(Object parameter);
}
