package com.acg12.service.impl;

import com.acg12.entity.po.Acg12BannerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg12.dao.Acg12BannerDao;
import com.acg12.service.Acg12BannerService;
import java.util.List;
/**
 * SERVICE - Acg12BannerEntity(banner)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12BannerServiceImpl extends GenericServiceImpl<Acg12BannerEntity, Long> implements Acg12BannerService {
	
	@Autowired
	private Acg12BannerDao acg12BannerDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12BannerDao);
	}

	public List<Acg12BannerEntity> findListByPage(Object parameter){
		return acg12BannerDao.findListByPage(parameter);
	}
	
	public List<Acg12BannerEntity> findListNewByPage(Object parameter) {
		return acg12BannerDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12BannerDao.deletes( parameter);
	}
	
	
}
