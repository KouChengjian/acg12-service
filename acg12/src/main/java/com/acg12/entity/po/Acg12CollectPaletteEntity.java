package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - CollectPalette
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_COLLECT_PALETTE")
public class Acg12CollectPaletteEntity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/** userId */
	@Column(name = "user_id" )
	private Long userId;
	
	/** boardId */
	@Column(name = "board_id" )
	private String boardId;
	
	/** 标题 */
	@Column(name = "title" )
	private String title;
	
	/** 签名 */
	@Column(name = "sign" )
	private String sign;
	
	/** 数量 */
	@Column(name = "num" )
	private Integer num;

	/** 封面 */
	@Column(name = "cover" )
	private String cover;

	/** 缩略图1 */
	@Column(name = "thum_image_1" )
	private String thumImage1;

	/** 缩略图2 */
	@Column(name = "thum_image_2" )
	private String thumImage2;

	/** 缩略图3 */
	@Column(name = "thum_image_3" )
	private String thumImage3;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 创建时间 */
	@Column(name = "update_time" )
	private java.util.Date updateTime;

	/** 是否收藏 */
	private Integer isCollect;
	
}
