package com.acg12.service;

import com.acg12.entity.po.Acg12CharacterDetailEntity;

import java.util.List;

/**
 * SERVICE - Acg12CharacterDetail(characterDetail)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12CharacterDetailService extends GenericService<Acg12CharacterDetailEntity, Long> {
    public List<Acg12CharacterDetailEntity> findListByPage(Object parameter);

    public List<Acg12CharacterDetailEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);
}
