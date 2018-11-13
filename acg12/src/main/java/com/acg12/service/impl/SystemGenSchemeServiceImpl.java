package com.acg12.service.impl;

import com.acg12.dao.SystemGenSchemeDao;
import com.acg12.entity.po.SystemGenSchemeEntity;
import com.acg12.service.SystemGenSchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - 生成方案
 * 
 * @author Mounate Yan。
 * @version 1.0
 */
@Service("genSchemeServiceImpl")
public class SystemGenSchemeServiceImpl extends GenericServiceImpl<SystemGenSchemeEntity, Long> implements SystemGenSchemeService {

	@Autowired
	private SystemGenSchemeDao genSchemeDao;
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(genSchemeDao);
	}
}