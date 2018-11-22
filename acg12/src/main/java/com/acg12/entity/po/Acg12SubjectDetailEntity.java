package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - subjectDetail
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_SUBJECT_DETAIL")
public class Acg12SubjectDetailEntity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Integer id;
	
	/**  */
	@Column(name = "subject_id" )
	private Integer subjectId;
	
	/** 外部获取的id */
	@Column(name = "s_id" )
	private Integer sId;
	
	/** 标题 */
	@Column(name = "other_title" )
	private String otherTitle;
	
	/** 值 */
	@Column(name = "other_value" )
	private String otherValue;
	
}
