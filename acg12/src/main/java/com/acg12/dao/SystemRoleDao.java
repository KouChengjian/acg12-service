package com.acg12.dao;

import com.acg12.entity.po.SystemRoleEntity;
import com.framework.loippi.mybatis.dao.GenericDao;
import org.apache.ibatis.annotations.Param;

/**
 * DAO - ROLE
 * 
 * @author Loippi Team
 * @version 1.0
 */
public interface SystemRoleDao extends GenericDao<SystemRoleEntity, Long> {
	
	/**
	 * 查找ROLE并同时加载关联的ACL列表
	 * @param id
	 * @return
	 */
	SystemRoleEntity findRoleAndAcls(@Param("id") Long id);
}
