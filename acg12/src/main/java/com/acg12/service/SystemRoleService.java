package com.acg12.service;

import com.acg12.entity.po.SystemRoleEntity;
import org.apache.ibatis.annotations.Param;

/**
 * SERVICE - ROLE
 * 
 * @author Loippi Team
 * @version 1.0
 */
public interface SystemRoleService extends GenericService<SystemRoleEntity, Long> {

	/**
	 * 查找角色并加载资源列表
	 * 
	 * @param id
	 *            ROLEID
	 * @return
	 */
	SystemRoleEntity findRoleAndAcls(@Param("id") Long id);

	/**
	 * 保存角色
	 * 
	 * @param role
	 *            角色
	 * @param ids
	 *            资源ID列表
	 */
	void save(SystemRoleEntity role, Long... ids);
	
	
	/**
	 * 修改角色
	 * 
	 * @param role
	 *            角色
	 * @param ids
	 *            资源ID列表
	 */
	void update(SystemRoleEntity role, Long... ids);
}
