package com.acg12.service;

import com.acg12.entity.po.SystemRoleAclEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/9 14:49
 * Description:
 */
public interface SystemRoleAclService extends GenericService<SystemRoleAclEntity, Long> {

    /**
     * 根据ACL_ID删除关联关系
     * @param aclId
     */
    void deleteByAclId(@Param("aclId") Long aclId);
}
