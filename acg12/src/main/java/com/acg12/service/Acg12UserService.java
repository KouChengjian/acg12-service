package com.acg12.service;

import com.acg12.entity.po.Acg12UserEntity;

import java.util.List;

/**
 * SERVICE - Acg12UserEntity(用户表)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12UserService extends GenericService<Acg12UserEntity, Long> {
    public List<Acg12UserEntity> findListByPage(Object parameter);

    public List<Acg12UserEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);

    public Acg12UserEntity findUserByPhone(String phone , String password);

    public Acg12UserEntity findUserByEmail(String email, String password);
}
