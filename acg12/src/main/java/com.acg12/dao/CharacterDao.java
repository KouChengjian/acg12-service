package com.acg12.dao;

import com.acg12.entity.po.CharacterEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface CharacterDao {

    int insert(CharacterEntity personEntity);

    int update(CharacterEntity personEntity);

    List<CharacterEntity> queryList();
}
