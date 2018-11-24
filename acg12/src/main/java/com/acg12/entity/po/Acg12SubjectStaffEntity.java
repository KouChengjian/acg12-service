package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - subjectStaff
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_SUBJECT_STAFF")
public class Acg12SubjectStaffEntity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Integer id;
	
	/**  */
	@Column(name = "subject_id" )
	private Integer subjectId;
	
	/**  */
	@Column(name = "s_id" )
	private Integer sId;
	
	/**  */
	@Column(name = "person_id" )
	private Integer personId;
	
	/**  */
	@Column(name = "p_id" )
	private Integer pId;
	
	/** 名称 */
	@Column(name = "name" )
	private String name;
	
	/** 职业 */
	@Column(name = "job" )
	private String job;
	
	/**  */
	@Column(name = "createTime" )
	private Integer createtime;
	
	/**  */
	@Column(name = "updateTime" )
	private Integer updatetime;

	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;

	/** 修改时间 */
	@Column(name = "update_time" )
	private java.util.Date updateTime;
}
