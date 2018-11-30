package com.acg12.service.impl;

import com.acg12.entity.po.Acg12PersonDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg12.dao.Acg12PersonDetailDao;
import com.acg12.service.Acg12PersonDetailService;
import java.util.List;
/**
 * SERVICE - Acg12PersonDetail(personDetail)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12PersonDetailServiceImpl extends GenericServiceImpl<Acg12PersonDetailEntity, Long> implements Acg12PersonDetailService {
	
	@Autowired
	private Acg12PersonDetailDao acg12PersonDetailDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12PersonDetailDao);
	}

	public List<Acg12PersonDetailEntity> findListByPage(Object parameter){
		return acg12PersonDetailDao.findListByPage(parameter);
	}
	
	public List<Acg12PersonDetailEntity> findListNewByPage(Object parameter) {
		return acg12PersonDetailDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12PersonDetailDao.deletes( parameter);
	}
	
	
}
