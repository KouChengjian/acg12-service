package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - banner
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_BANNER")
public class Acg12BannerEntity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** 标题 */
	@Column(name = "title" )
	private String title;
	
	/** 类型 */
	@Column(name = "type" )
	private Integer type;
	
	/** 类型名 */
	@Column(name = "type_name" )
	private String typeName;
	
	/** 锁定 */
	@Column(name = "is_lock" )
	private Integer isLock;
	
	/** 图片 */
	@Column(name = "cover" )
	private String cover;
	
	/** 排序 */
	@Column(name = "sort" )
	private Integer sort;
	
	/** 内容 */
	@Column(name = "content" )
	private String content;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 修改时间 */
	@Column(name = "update_time" )
	private java.util.Date updateTime;
	
}
