package com.acg12.dao;

import com.acg12.entity.po.Acg12CharacterDetailEntity;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.util.List;

/**
 * DAO - Acg12CharacterDetail(characterDetail)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12CharacterDetailDao extends GenericDao<Acg12CharacterDetailEntity, Long> {
    List<Acg12CharacterDetailEntity> findListByPage(Object parameter);

    List<Acg12CharacterDetailEntity> findListNewByPage(Object parameter);

    Long deletes(Object parameter);
}
