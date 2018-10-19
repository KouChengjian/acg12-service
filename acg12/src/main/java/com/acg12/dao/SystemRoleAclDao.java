package com.acg12.dao;

import com.acg12.entity.po.SystemRoleAclEntity;
import com.framework.loippi.mybatis.dao.GenericDao;
import org.apache.ibatis.annotations.Param;

/**
 * DAO - ROLE-ACLS
 * 
 * @author Loippi Team
 * @version 1.0
 */
public interface SystemRoleAclDao extends GenericDao<SystemRoleAclEntity, Long> {
	
	void deleteByAclId(@Param("aclId") Long aclId);
	
	
	void deleteByRoleId(@Param("roleId") Long roleId);
	
}
