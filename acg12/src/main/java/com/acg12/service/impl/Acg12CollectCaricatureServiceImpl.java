package com.acg12.service.impl;

import com.acg12.dao.Acg12CollectCaricatureDao;
import com.acg12.entity.po.Acg12CollectCaricatureEntity;
import com.acg12.service.Acg12CollectCaricatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * SERVICE - Acg12CollectCaricatureEntity(CollectCaricature)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12CollectCaricatureServiceImpl extends GenericServiceImpl<Acg12CollectCaricatureEntity, Long> implements Acg12CollectCaricatureService {
	
	@Autowired
	private Acg12CollectCaricatureDao acg12CollectCaricatureDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12CollectCaricatureDao);
	}

	public List<Acg12CollectCaricatureEntity> findListByPage(Object parameter){
		return acg12CollectCaricatureDao.findListByPage(parameter);
	}
	
	public List<Acg12CollectCaricatureEntity> findListNewByPage(Object parameter) {
		return acg12CollectCaricatureDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12CollectCaricatureDao.deletes( parameter);
	}
	
	
}
