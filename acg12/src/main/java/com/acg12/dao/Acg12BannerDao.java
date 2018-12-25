package com.acg12.dao;

import com.acg12.entity.po.Acg12BannerEntity;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - Acg12BannerEntity(banner)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12BannerDao  extends GenericDao<Acg12BannerEntity, Long> {
	List<Acg12BannerEntity> findListByPage(Object parameter);
	List<Acg12BannerEntity> findListNewByPage(Object parameter);
    Long deletes(Object parameter);
}
