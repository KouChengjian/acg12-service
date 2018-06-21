package com.acg12.modules.app.dao.character;

import com.acg12.common.pagination.PageInfo;
import com.acg12.modules.app.entity.po.character.CharacterEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface CharacterDao {

    int insert(CharacterEntity characterEntity);

    int update(CharacterEntity characterEntity);

    List<CharacterEntity> queryList();

    List<CharacterEntity> queryByCharacterListPage(@Param("page") PageInfo page, @Param("type") String type, @Param("gender") int gender, @Param("bloodtype") int bloodtype, @Param("birthday") String birthday);

    CharacterEntity queryByCId(int cId);

    CharacterEntity queryByCharacterId(int characterId);

    CharacterEntity queryByCharacterIdJoinDetail(int characterId);
}