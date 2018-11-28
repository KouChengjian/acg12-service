package com.acg12.dao;

import com.acg12.entity.po.Acg12ScheduleJobEntity;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - Acg12ScheduleJob(scheduleJob)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12ScheduleJobDao  extends GenericDao<Acg12ScheduleJobEntity, Long> {
	List<Acg12ScheduleJobEntity> findListByPage(Object parameter);
	List<Acg12ScheduleJobEntity> findListNewByPage(Object parameter);
    Long deletes(Object parameter);
}
