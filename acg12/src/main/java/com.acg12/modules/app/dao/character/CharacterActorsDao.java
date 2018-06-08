package com.acg12.modules.app.dao.character;

import com.acg12.modules.app.entity.po.character.CharacterActorsEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */
public interface CharacterActorsDao {

    int insert(CharacterActorsEntity subjectEntity);

    int insertList(List<CharacterActorsEntity> list);

    int update(CharacterActorsEntity subjectEntity);

    CharacterActorsEntity queryByCharacterActorsId(int characterActorsId);

    CharacterActorsEntity queryByActors(@Param(value = "characterId")int characterId , @Param(value = "personId")int personId);

    CharacterActorsEntity queryByCharacterId(int characterId);

    CharacterActorsEntity queryByPersonId(int personId);
}
