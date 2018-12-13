package com.acg12.service;

import com.acg12.entity.po.Acg12ScheduleJobLogEntity;

import java.util.List;
/**
 * SERVICE - Acg12ScheduleJobLog(scheduleJobLog)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12ScheduleJobLogService  extends GenericService<Acg12ScheduleJobLogEntity, Long> {
	public	List<Acg12ScheduleJobLogEntity> findListByPage(Object parameter);
	public	List<Acg12ScheduleJobLogEntity> findListNewByPage(Object parameter);
    public	 Long deletes(Object parameter);
}
