package com.acg12.service.impl;

import com.acg12.entity.po.Acg12SubjectDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg12.dao.Acg12SubjectDetailDao;
import com.acg12.service.Acg12SubjectDetailService;
import java.util.List;
/**
 * SERVICE - Acg12SubjectDetail(subjectDetail)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12SubjectDetailServiceImpl extends GenericServiceImpl<Acg12SubjectDetailEntity, Long> implements Acg12SubjectDetailService {
	
	@Autowired
	private Acg12SubjectDetailDao acg12SubjectDetailDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12SubjectDetailDao);
	}

	public List<Acg12SubjectDetailEntity> findListByPage(Object parameter){
		return acg12SubjectDetailDao.findListByPage(parameter);
	}
	
	public List<Acg12SubjectDetailEntity> findListNewByPage(Object parameter) {
		return acg12SubjectDetailDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12SubjectDetailDao.deletes( parameter);
	}
	
	
}
