package com.acg12.service;


import com.acg12.entity.po.SystemGenTableColumnEntity;

/**
 * SERVICE - GenTable
 * 
 * @author Loippi Team
 * @version 1.0
 */
public interface SystemGenTableColumnService extends GenericService<SystemGenTableColumnEntity, Long> {
	
	/**
	 * 根据Table编号删除所有列
	 * @param tableId
	 * @return
	 */
	Long deleteByTableId(Long tableId);
}
