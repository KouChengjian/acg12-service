package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - character
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_CHARACTER")
public class Acg12CharacterEntity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Integer id;
	
	/** c_id */
	@Column(name = "c_id" )
	private Integer cId;
	
	/** 名称 */
	@Column(name = "name" )
	private String name;
	
	/** 中文名称 */
	@Column(name = "name_cn" )
	private String nameCn;
	
	/** 图片 */
	@Column(name = "image" )
	private String image;
	
	/** 简介 */
	@Column(name = "summary" )
	private String summary;
	
	/** 身高 */
	@Column(name = "height" )
	private String height;
	
	/** 体重 */
	@Column(name = "weight" )
	private String weight;
	
	/** 别名 */
	@Column(name = "alias" )
	private String alias;
	
	/** 类型 1、角色 2、机体 3、舰船 4、组织机构 */
	@Column(name = "type" )
	private String type;
	
	/** 性别 1：男 2：女 */
	@Column(name = "gender" )
	private Integer gender;
	
	/** 血型  1、A   2、B   3、AB   4、O */
	@Column(name = "bloodtype" )
	private Integer bloodtype;
	
	/** 生日 */
	@Column(name = "birthday" )
	private String birthday;

	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 更新时间 */
	@Column(name = "update_time" )
	private java.util.Date updateTime;
	
}
