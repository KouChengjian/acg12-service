package com.acg12.service.impl;

import com.acg12.dao.SystemUserDao;
import com.acg12.entity.po.SystemUserEntity;
import com.acg12.service.SystemUserService;
import com.acg12.shiro.Principal;
import com.framework.loippi.utils.Paramap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/10/17 11:38
 * Description:
 */
@Service("systemUserServiceImpl")
public class SystemUserServiceImpl extends GenericServiceImpl<SystemUserEntity, Long> implements SystemUserService {

    @Autowired
    private SystemUserDao systemUserDao;

    @Autowired
    public void setGenericDao() {
        super.setGenericDao(systemUserDao);
    }

    @Transactional(readOnly = true)
    public SystemUserEntity getCurrent() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null && principal.getId() != null) {
                return systemUserDao.find(principal.getId());
            }
        }
        return null;
    }

    @Transactional(readOnly = true)
    public String getCurrentUsername() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return principal.getUsername();
            }
        }
        return null;
    }

    public boolean usernameExists(String usernames) {
        List<SystemUserEntity> users = systemUserDao.findByParams(Paramap.create().put("username", usernames));
        return CollectionUtils.isNotEmpty(users);
    }


}
