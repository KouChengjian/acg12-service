package com.acg12.service;

import com.acg12.entity.po.SystemUserEntity;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/10/17 11:36
 * Description:
 */
public interface SystemUserService extends GenericService<SystemUserEntity, Long> {

    /**
     * 获取当前登录管理员
     *
     * @return 当前登录管理员,若不存在则返回null
     */
    SystemUserEntity getCurrent();

    /**
     * 获取当前登录用户名
     *
     * @return 当前登录用户名,若不存在则返回null
     */
    String getCurrentUsername();


    /**
     * 检查用户名是否存在
     */
    boolean usernameExists(String usernames);

}
