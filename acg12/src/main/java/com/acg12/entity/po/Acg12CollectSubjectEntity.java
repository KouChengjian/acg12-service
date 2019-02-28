package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - collectSubject
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_COLLECT_SUBJECT")
public class Acg12CollectSubjectEntity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Long id;

	/** 用户id */
	@Column(name = "user_id" )
	private Long userId;
	
	/** 关联id */
	@Column(name = "relevance_id" )
	private Integer relevanceId;

	/** 图片 */
	@Column(name = "image" )
	private String image;

	/** 名称 */
	@Column(name = "name" )
	private String name;
	
	/** 中文名称 */
	@Column(name = "name_cn" )
	private String nameCn;
	
	/** 0:subject 1:crt 2:preson */
	@Column(name = "type" )
	private Integer type;
	
	/** 类型名称 */
	@Column(name = "type_name" )
	private String typeName;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 更新时间 */
	@Column(name = "update_time" )
	private java.util.Date updateTime;

	/** 是否收藏 */
	private Integer isCollect;
	
}
