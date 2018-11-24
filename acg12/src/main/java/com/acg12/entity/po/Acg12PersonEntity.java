package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - person
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_PERSON")
public class Acg12PersonEntity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Integer id;
	
	/** pId */
	@Column(name = "p_id" )
	private Integer pId;
	
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
	
	/**  1、声优 2、漫画家 3、制作人 4、音乐人 5、 6、演员 7、绘师 8、作家 */
	@Column(name = "type" )
	private String type;
	
	/** // 1、男 2、女 */
	@Column(name = "gender" )
	private String gender;
	
	/** 血型  1、A   2、B   3、AB   4、O */
	@Column(name = "bloodtype" )
	private Integer bloodtype;
	
	/** 生日 */
	@Column(name = "birthday" )
	private String birthday;
	
	/**  */
	@Column(name = "createTime" )
	private Integer createtime;
	
	/**  */
	@Column(name = "updateTime" )
	private Integer updatetime;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 更新时间 */
	@Column(name = "update_time" )
	private java.util.Date updateTime;
	
}
