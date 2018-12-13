package com.acg12.dao;

import com.acg12.entity.po.Acg12ScheduleJobLogEntity;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - Acg12ScheduleJobLogEntity(scheduleJobLog)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12ScheduleJobLogDao  extends GenericDao<Acg12ScheduleJobLogEntity, Long> {
	List<Acg12ScheduleJobLogEntity> findListByPage(Object parameter);
	List<Acg12ScheduleJobLogEntity> findListNewByPage(Object parameter);
    Long deletes(Object parameter);
}
