package com.acg12.service.impl;

import com.acg12.entity.po.Acg12ScheduleJobLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg12.dao.Acg12ScheduleJobLogDao;
import com.acg12.service.Acg12ScheduleJobLogService;
import java.util.List;
/**
 * SERVICE - Acg12ScheduleJobLog(scheduleJobLog)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12ScheduleJobLogServiceImpl extends GenericServiceImpl<Acg12ScheduleJobLogEntity, Long> implements Acg12ScheduleJobLogService {
	
	@Autowired
	private Acg12ScheduleJobLogDao acg12ScheduleJobLogDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12ScheduleJobLogDao);
	}

	public List<Acg12ScheduleJobLogEntity> findListByPage(Object parameter){
		return acg12ScheduleJobLogDao.findListByPage(parameter);
	}
	
	public List<Acg12ScheduleJobLogEntity> findListNewByPage(Object parameter) {
		return acg12ScheduleJobLogDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12ScheduleJobLogDao.deletes( parameter);
	}
	
	
}
