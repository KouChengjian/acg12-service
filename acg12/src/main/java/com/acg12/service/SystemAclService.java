package com.acg12.service;

import com.acg12.entity.po.SystemAclEntity;

import java.util.List;

public interface SystemAclService extends GenericService<SystemAclEntity, Long> {

    /**
     * 主键删除ACL
     */
    Long delete(Long id);

    /**
     * 查找ROOT菜单
     */
    List<SystemAclEntity> findRoots();

    /**
     * 根据ID获取子菜单
     */
    List<SystemAclEntity> findChildrens(Long id);


    List<SystemAclEntity> findByParams(Object parameter);
}

