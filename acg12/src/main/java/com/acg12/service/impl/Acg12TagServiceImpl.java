package com.acg12.service.impl;

import com.acg12.entity.po.Acg12Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg12.dao.Acg12TagDao;
import com.acg12.service.Acg12TagService;
import java.util.List;
/**
 * SERVICE - Acg12Tag(标签)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12TagServiceImpl extends GenericServiceImpl<Acg12Tag, Long> implements Acg12TagService {
	
	@Autowired
	private Acg12TagDao acg12TagDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12TagDao);
	}

	public List<Acg12Tag> findListByPage(Object parameter){
		return acg12TagDao.findListByPage(parameter);
	}
	
	public List<Acg12Tag> findListNewByPage(Object parameter) {
		return acg12TagDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12TagDao.deletes( parameter);
	}
	
	
}
