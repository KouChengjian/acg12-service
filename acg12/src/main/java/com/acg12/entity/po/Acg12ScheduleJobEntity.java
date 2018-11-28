package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - scheduleJob
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_SCHEDULE_JOB")
public class Acg12ScheduleJobEntity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;
	public static final String STATUS_RUNNING = "1";
	public static final String STATUS_NOT_RUNNING = "0";
	public static final String CONCURRENT_IS = "1";
	public static final String CONCURRENT_NOT = "0";

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Integer id;
	
	/** 任务名称 */
	@Column(name = "job_name" )
	private String jobName;
	
	/** 任务分组 */
	@Column(name = "job_group" )
	private String jobGroup;
	
	/** 0 未开启 1 开启 */
	@Column(name = "job_status" )
	private String jobStatus;
	
	/** cron表达式 */
	@Column(name = "cron_expression" )
	private String cronExpression;
	
	/** 描述 */
	@Column(name = "description" )
	private String description;
	
	/** 任务执行时调用哪个类的方法 包名+类名 */
	@Column(name = "bean_class" )
	private String beanClass;
	
	/** 任务是否有状态 */
	@Column(name = "is_concurrent" )
	private String isConcurrent;
	
	/** 任务调用的方法名 */
	@Column(name = "method_name" )
	private String methodName;
	
	/** spring bean */
	@Column(name = "spring_id" )
	private String springId;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 更新时间 */
	@Column(name = "update_time" )
	private java.util.Date updateTime;
	
}
