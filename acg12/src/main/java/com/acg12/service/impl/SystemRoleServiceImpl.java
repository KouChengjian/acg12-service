package com.acg12.service.impl;

import com.acg12.dao.SystemRoleAclDao;
import com.acg12.dao.SystemRoleDao;
import com.acg12.entity.po.SystemRoleAclEntity;
import com.acg12.entity.po.SystemRoleEntity;
import com.acg12.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service - ROLE
 * 
 * @author LinkCity Team
 * @version 3.0
 */
@Service("systemRoleServiceImpl")
public class SystemRoleServiceImpl extends GenericServiceImpl<SystemRoleEntity, Long> implements SystemRoleService {
	
	@Autowired
	private SystemRoleDao roleDao;
	
	@Autowired
	private SystemRoleAclDao roleAclDao;
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(roleDao);
	}

	public SystemRoleEntity findRoleAndAcls(Long id) {
		return roleDao.findRoleAndAcls(id);
	}
	
	
	@Override
	public Long delete(Long id){
		roleAclDao.deleteByRoleId(id);
		return roleDao.delete(id);
	}

	public void save(SystemRoleEntity role, Long... ids) {
		Long roleId = roleDao.insert(role);
		for (Long aclId : ids) {
			roleAclDao.insertEntity(new SystemRoleAclEntity(roleId, aclId));
		}
		
	}

	public void update(SystemRoleEntity role, Long... ids) {
		roleDao.update(role);
		roleAclDao.deleteByRoleId(role.getId());
		for (Long aclId : ids) {
			roleAclDao.insertEntity(new SystemRoleAclEntity(role.getId(), aclId));
		}
	}
	
}
