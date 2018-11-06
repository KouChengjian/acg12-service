package com.acg12.service.impl;

import com.acg12.dao.SystemAclDao;
import com.acg12.dao.SystemRoleAclDao;
import com.acg12.entity.po.SystemAclEntity;
import com.acg12.service.SystemAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/6 10:29
 * Description:
 */
@Service("systemAclServiceImpl")
public class SystemAclServiceImpl extends GenericServiceImpl<SystemAclEntity, Long> implements SystemAclService {

    @Autowired
    private SystemAclDao aclDao;
    @Autowired
    private SystemRoleAclDao roleAclDao;


    @Autowired
    public void setGenericDao() {
        super.setGenericDao(aclDao);
    }


    public Long delete(Long id){
        roleAclDao.deleteByAclId(id);
        return aclDao.delete(id);
    }

    public List<SystemAclEntity> findRoots() {
        return aclDao.findRoots();
    }

    public List<SystemAclEntity> findChildrens(Long id) {
        return aclDao.findChildrens(id);
    }

    @Override
    public List<SystemAclEntity> findByParams(Object parameter) {
        return aclDao.findByParams(parameter);
    }
}
