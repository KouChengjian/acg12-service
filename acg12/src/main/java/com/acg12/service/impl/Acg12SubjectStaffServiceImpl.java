package com.acg12.service.impl;

import com.acg12.entity.po.Acg12SubjectStaffEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg12.dao.Acg12SubjectStaffDao;
import com.acg12.service.Acg12SubjectStaffService;
import java.util.List;
/**
 * SERVICE - Acg12SubjectStaff(subjectStaff)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12SubjectStaffServiceImpl extends GenericServiceImpl<Acg12SubjectStaffEntity, Long> implements Acg12SubjectStaffService {
	
	@Autowired
	private Acg12SubjectStaffDao acg12SubjectStaffDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12SubjectStaffDao);
	}

	public List<Acg12SubjectStaffEntity> findListByPage(Object parameter){
		return acg12SubjectStaffDao.findListByPage(parameter);
	}
	
	public List<Acg12SubjectStaffEntity> findListNewByPage(Object parameter) {
		return acg12SubjectStaffDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12SubjectStaffDao.deletes( parameter);
	}
	
	
}
