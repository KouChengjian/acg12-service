package com.acg12.service;

import com.acg12.entity.po.Acg12ScheduleJobEntity;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * SERVICE - Acg12ScheduleJob(scheduleJob)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12ScheduleJobService extends GenericService<Acg12ScheduleJobEntity, Long> {
    public List<Acg12ScheduleJobEntity> findListByPage(Object parameter);

    public List<Acg12ScheduleJobEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);

    public void changeStatus(Long jobId, String cmd) throws SchedulerException;
}
