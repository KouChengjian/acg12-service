package com.acg12.service.impl;

import com.acg12.entity.po.Acg12SubjectCrtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg12.dao.Acg12SubjectCrtDao;
import com.acg12.service.Acg12SubjectCrtService;
import java.util.List;
/**
 * SERVICE - Acg12SubjectCrt(subjectCrt)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12SubjectCrtServiceImpl extends GenericServiceImpl<Acg12SubjectCrtEntity, Long> implements Acg12SubjectCrtService {
	
	@Autowired
	private Acg12SubjectCrtDao acg12SubjectCrtDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12SubjectCrtDao);
	}

	public List<Acg12SubjectCrtEntity> findListByPage(Object parameter){
		return acg12SubjectCrtDao.findListByPage(parameter);
	}
	
	public List<Acg12SubjectCrtEntity> findListNewByPage(Object parameter) {
		return acg12SubjectCrtDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12SubjectCrtDao.deletes( parameter);
	}
	
	
}
