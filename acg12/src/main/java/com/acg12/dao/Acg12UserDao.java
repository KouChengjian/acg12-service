package com.acg12.dao;

import com.acg12.entity.po.Acg12UserEntity;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - Acg12UserEntity(用户表)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12UserDao  extends GenericDao<Acg12UserEntity, Long> {
	List<Acg12UserEntity> findListByPage(Object parameter);
	List<Acg12UserEntity> findListNewByPage(Object parameter);
    Long deletes(Object parameter);
}
