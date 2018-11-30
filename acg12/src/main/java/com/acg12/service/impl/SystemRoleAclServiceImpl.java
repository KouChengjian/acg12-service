package com.acg12.service.impl;

import com.acg12.dao.SystemRoleAclDao;
import com.acg12.entity.po.SystemRoleAclEntity;
import com.acg12.service.SystemRoleAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - ROLE-ACL
 * 
 * @author Loippi Team
 * @version 1.0
 */
@Service("systemRoleAclServiceImpl")
public class SystemRoleAclServiceImpl extends GenericServiceImpl<SystemRoleAclEntity, Long> implements SystemRoleAclService {
	
	@Autowired
	private SystemRoleAclDao roleAclDao;
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(roleAclDao);
	}

	public void deleteByAclId(Long aclId) {
		roleAclDao.deleteByAclId(aclId);
	}
	
	
}
