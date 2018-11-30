package com.acg12.dao;

import com.acg12.entity.po.SystemAclEntity;
import com.framework.loippi.mybatis.dao.GenericDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DAO - ACL
 * 
 * @author Loippi Team
 * @version 1.0
 */
public interface SystemAclDao extends GenericDao<SystemAclEntity, Long> {
	
	/**
	 * 查找顶级资源列表
	 * @return
	 */
	List<SystemAclEntity> findRoots();
	
	
	/**
	 * 查找资源列表
	 * @return
	 */
	List<SystemAclEntity> findByRoleId(@Param("roleId") Long roleId);

	/**
	 * 查找子类资源列表
	 * @param id
	 * @return
	 */
	List<SystemAclEntity> findChildrens(@Param("id") Long id);
	
	List<SystemAclEntity> findByParams(Object parameter);
}
