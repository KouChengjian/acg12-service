package com.acg12.dao.character;

import com.acg12.entity.po.character.CharacterDetailEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface CharacterDetailDao {

    int insert(CharacterDetailEntity characterDetailEntity);

    int insertList(List<CharacterDetailEntity> list);

    int update(CharacterDetailEntity characterDetailEntity);

    List<CharacterDetailEntity> queryList();

    List<CharacterDetailEntity> queryByCharacterId(int characterId);
}
