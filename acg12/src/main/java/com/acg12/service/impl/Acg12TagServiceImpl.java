package com.acg12.service.impl;

import com.acg12.entity.po.Acg12TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg12.dao.Acg12TagDao;
import com.acg12.service.Acg12TagService;
import java.util.List;
/**
 * SERVICE - Acg12TagEntity(标签)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12TagServiceImpl extends GenericServiceImpl<Acg12TagEntity, Long> implements Acg12TagService {
	
	@Autowired
	private Acg12TagDao acg12TagDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12TagDao);
	}

	public List<Acg12TagEntity> findListByPage(Object parameter){
		return acg12TagDao.findListByPage(parameter);
	}
	
	public List<Acg12TagEntity> findListNewByPage(Object parameter) {
		return acg12TagDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12TagDao.deletes( parameter);
	}
	
	
}
