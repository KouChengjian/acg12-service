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
	
	/** 创建时间 */
	@Column(name = "createTime" )
	private java.util.Date createtime;
	
	/** 创建时间 */
	@Column(name = "updateTime" )
	private java.util.Date updatetime;
	
}
