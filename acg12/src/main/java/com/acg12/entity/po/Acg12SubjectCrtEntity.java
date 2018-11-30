package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - subjectCrt
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_SUBJECT_CRT")
public class Acg12SubjectCrtEntity implements GenericEntity {

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
	
	/** 外部获取的id - 虚拟人物 */
	@Column(name = "c_id" )
	private Integer cId;
	
	/** 外部获取的id - 真实人物 */
	@Column(name = "p_id" )
	private Integer pId;
	
	/** 名称 */
	@Column(name = "name" )
	private String name;
	
	/** 中文名 */
	@Column(name = "name_n" )
	private String nameN;
	
	/** 角色名 */
	@Column(name = "role_name" )
	private String roleName;
	
	/** 图片 */
	@Column(name = "image" )
	private String image;
	
	/** cv-名称 */
	@Column(name = "p_name" )
	private String pName;
	
	/**  */
	@Column(name = "p_name_cn" )
	private String pNameCn;
	
	/**  */
	@Column(name = "p_image" )
	private String pImage;
	
	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;
	
	/** 修改时间 */
	@Column(name = "update_time" )
	private java.util.Date updateTime;
	
}
