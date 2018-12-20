package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - 用户表
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_USER")
public class Acg12UserEntity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Long id;

	/** 账号 */
	@Column(name = "username" )
	private String username;

	/** 密码 */
	@Column(name = "password" )
	private String password;

	/** 邮箱 */
	@Column(name = "email" )
	private String email;

	/** 头像 */
	@Column(name = "avatar" )
	private String avatar;

	/** 昵称 */
	@Column(name = "nick" )
	private String nick;

	/** 性别 */
	@Column(name = "sex" )
	private Integer sex;

	/** 签名 */
	@Column(name = "sign" )
	private String sign;

	/** 创建时间 */
	@Column(name = "create_time" )
	private java.util.Date createTime;

	/** 更新时间 */
	@Column(name = "update_time" )
	private java.util.Date updateTime;
	
}
