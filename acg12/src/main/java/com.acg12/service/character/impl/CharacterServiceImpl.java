package com.acg12.service.character.impl;

import com.acg12.dao.character.CharacterDao;
import com.acg12.entity.po.character.CharacterEntity;
import com.acg12.service.character.CharacterService;
import com.acg12.utils.pagination.PageInfo;
import org.springframework.stereotype.Service;

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
