package com.acg12.modules.app.service.impl;

import com.acg12.modules.app.dao.character.CharacterDao;
import com.acg12.modules.app.entity.po.character.CharacterEntity;
import com.acg12.modules.app.service.CharacterService;
import com.acg12.common.pagination.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
public class CharacterServiceImpl implements CharacterService {

    @Resource
    CharacterDao characterDao;

    @Override
    public List<CharacterEntity> queryByCharacterList(PageInfo pageInfo, String type, int gender, int bloodtype, String birthday) {
        return characterDao.queryByCharacterListPage(pageInfo ,type, gender, bloodtype, birthday);
    }

    @Override
    public CharacterEntity queryByCharacterIdJoinDetail(int characterId) {
        return characterDao.queryByCharacterIdJoinDetail(characterId);
    }
}
