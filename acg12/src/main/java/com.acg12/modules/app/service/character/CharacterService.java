package com.acg12.modules.app.service.character;

import com.acg12.common.pagination.PageInfo;
import com.acg12.modules.app.entity.po.character.CharacterEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
public interface CharacterService {

    List<CharacterEntity> queryByCharacterList(PageInfo pageInfo , String type, int gender, int bloodtype, String birthday);

    CharacterEntity queryByCharacterIdJoinDetail(int characterId);
}
