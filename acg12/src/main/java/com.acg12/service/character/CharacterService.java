package com.acg12.service.character;

import com.acg12.entity.po.character.CharacterEntity;
import com.acg12.utils.pagination.PageInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
public interface CharacterService {

    List<CharacterEntity> queryByCharacterList(PageInfo pageInfo , String type, int gender, int bloodtype, String birthday);

    CharacterEntity queryByCharacterIdJoinDetail(int characterId);
}
