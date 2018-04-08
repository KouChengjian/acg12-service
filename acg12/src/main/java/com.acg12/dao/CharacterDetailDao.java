package com.acg12.dao;

import com.acg12.entity.po.CharacterDetailEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface CharacterDetailDao {

    int insert(CharacterDetailEntity personEntity);

    int update(CharacterDetailEntity personEntity);

    List<CharacterDetailEntity> queryList();
}
