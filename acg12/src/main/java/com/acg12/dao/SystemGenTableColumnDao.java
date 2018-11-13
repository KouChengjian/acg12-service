package com.acg12.dao;

import com.acg12.entity.po.SystemGenTableColumnEntity;
import com.framework.loippi.mybatis.dao.GenericDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DAO - Gen Table
 * 
 * @author Loippi Team
 * @version 1.0
 */
public interface SystemGenTableColumnDao extends GenericDao<SystemGenTableColumnEntity, Long> {

	/**
	 * 获取列表
	 * @param tableId
	 * @return
	 */
	List<SystemGenTableColumnEntity> findByTableId(@Param("tableId") Long tableId);
	
	
	/**
	 * 根据Table编号删除所有列
	 * @param tableId
	 * @return
	 */
	Long deleteByTableId(@Param("tableId") Long tableId);
}
