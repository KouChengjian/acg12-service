package com.acg12.dao.character;

import com.acg12.entity.po.character.CharacterEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface CharacterDao {

    int insert(CharacterEntity characterEntity);

    int update(CharacterEntity characterEntity);

    List<CharacterEntity> queryList();

    CharacterEntity queryByCId(int cId);

    CharacterEntity queryByCharacterId(int characterId);
}
