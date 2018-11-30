package com.acg12.dao;

import com.acg12.entity.po.SystemUserEntity;
import com.framework.loippi.mybatis.dao.GenericDao;
import org.apache.ibatis.annotations.Param;

/**
 * DAO - USER
 * 
 * @author Loippi Team
 * @version 1.0
 */
public interface SystemUserDao extends GenericDao<SystemUserEntity, Long> {

	SystemUserEntity findUserAndRole(@Param("id") Long id);
}
