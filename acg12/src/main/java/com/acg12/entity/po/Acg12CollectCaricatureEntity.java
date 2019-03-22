package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - CollectCaricature
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_COLLECT_CARICATURE")
public class Acg12CollectCaricatureEntity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** userId */
	@Column(name = "user_id" )
	private Long userId;
	
	/** comicId */
	@Column(name = "comic_id" )
	private Long comicId;
	
	/** 类型 1:酷克 */
	@Column(name = "type" )
	private Integer type;
	
	/** 封面 */
	@Column(name = "cover" )
	private String cover;
	
	/** 标题 */
	@Column(name = "title" )
	private String title;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 更新时间 */
	@Column(name = "update_time" )
	private java.util.Date updateTime;

	/** 是否收藏 */
	private Integer isCollect;
	
}
