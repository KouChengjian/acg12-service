package com.acg12.service.impl;

import com.acg12.dao.SystemGenTableColumnDao;
import com.acg12.entity.po.SystemGenTableColumnEntity;
import com.acg12.service.SystemGenTableColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - 表字段
 * 
 * @author Mounate Yan。
 * @version 1.0
 */
@Service("genTableColumnServiceImpl")
public class SystemGenTableColumnServiceImpl extends GenericServiceImpl<SystemGenTableColumnEntity, Long> implements SystemGenTableColumnService {

	@Autowired
	private SystemGenTableColumnDao genTableColumnDao;
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(genTableColumnDao);
	}

	@Override
	public Long deleteByTableId(Long tableId) {
		return genTableColumnDao.deleteByTableId(tableId);
	}

}