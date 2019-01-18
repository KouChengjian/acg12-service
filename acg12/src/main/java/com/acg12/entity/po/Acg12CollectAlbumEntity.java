package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - CollectAlbum
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_COLLECT_ALBUM")
public class Acg12CollectAlbumEntity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Long id;
	
	/**  */
	@Column(name = "user_id" )
	private Long userId;
	
	/** 图片id */
	@Column(name = "pin_id" )
	private String pinId;
	
	/** 图片url */
	@Column(name = "image" )
	private String image;
	
	/** 外链 */
	@Column(name = "link_url" )
	private String linkUrl;
	
	/** 内容 */
	@Column(name = "content" )
	private String content;
	
	/** 点赞 */
	@Column(name = "love" )
	private Integer love;
	
	/** 采集 */
	@Column(name = "favorites" )
	private Integer favorites;
	
	/** 创建时间 */
	@Column(name = "createTime" )
	private java.util.Date createtime;
	
	/** 更新时间 */
	@Column(name = "updateTime" )
	private java.util.Date updatetime;
	
}
