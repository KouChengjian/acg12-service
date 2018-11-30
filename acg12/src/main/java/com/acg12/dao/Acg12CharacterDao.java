package com.acg12.dao;

import com.acg12.entity.po.Acg12CharacterEntity;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.util.List;

/**
 * DAO - Acg12Character(character)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12CharacterDao extends GenericDao<Acg12CharacterEntity, Long> {
    List<Acg12CharacterEntity> findListByPage(Object parameter);

    List<Acg12CharacterEntity> findListNewByPage(Object parameter);

    Long deletes(Object parameter);
}
