package com.acg12.service.impl;

import com.acg12.entity.po.Acg12ScheduleJobEntity;
import com.acg12.utils.TaskUtils;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.acg12.dao.Acg12ScheduleJobDao;
import com.acg12.service.Acg12ScheduleJobService;

import java.util.Date;
import java.util.List;
/**
 * SERVICE - Acg12ScheduleJob(scheduleJob)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12ScheduleJobServiceImpl extends GenericServiceImpl<Acg12ScheduleJobEntity, Long> implements Acg12ScheduleJobService {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	@Autowired
	private Acg12ScheduleJobDao acg12ScheduleJobDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12ScheduleJobDao);
	}

	public List<Acg12ScheduleJobEntity> findListByPage(Object parameter){
		return acg12ScheduleJobDao.findListByPage(parameter);
	}
	
	public List<Acg12ScheduleJobEntity> findListNewByPage(Object parameter) {
		return acg12ScheduleJobDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12ScheduleJobDao.deletes( parameter);
	}

	@Override
	public void changeStatus(Long jobId, String cmd) throws SchedulerException {
		Acg12ScheduleJobEntity scheduleJobEntity = acg12ScheduleJobDao.find(jobId);
		if (scheduleJobEntity == null) {
			return;
		}
		if ("0".equals(cmd)) {
			TaskUtils.deleteJob(schedulerFactoryBean ,scheduleJobEntity);
			scheduleJobEntity.setJobStatus(Acg12ScheduleJobEntity.STATUS_NOT_RUNNING);
		} else if ("1".equals(cmd)) {
			scheduleJobEntity.setJobStatus(Acg12ScheduleJobEntity.STATUS_RUNNING);
			TaskUtils.addJob(schedulerFactoryBean ,scheduleJobEntity);
		}
		acg12ScheduleJobDao.update(scheduleJobEntity);
	}
}
