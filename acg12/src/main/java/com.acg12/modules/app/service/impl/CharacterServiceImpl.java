package com.acg12.modules.app.service.impl;

import com.acg12.modules.app.dao.character.CharacterDao;
import com.acg12.modules.app.dao.character.CharacterDetailDao;
import com.acg12.modules.app.entity.dto.subject.CharacterInfoDto;
import com.acg12.modules.app.entity.po.character.CharacterDetailEntity;
import com.acg12.modules.app.entity.po.character.CharacterEntity;
import com.acg12.modules.app.service.CharacterService;
import com.acg12.common.pagination.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
@Service(value = "characterServiceImpl")
public class CharacterServiceImpl implements CharacterService {

    @Resource
    CharacterDao characterDao;
    @Resource
    CharacterDetailDao mCharacterDetailDao;

    @Override
    public List<CharacterEntity> queryByCharacterList(PageInfo pageInfo, String type, int gender, int bloodtype, String birthday) {
        return characterDao.queryByCharacterListPage(pageInfo ,type, gender, bloodtype, birthday);
    }

    @Override
    public CharacterEntity queryByCharacterIdJoinDetail(int characterId) {
        return characterDao.queryByCharacterIdJoinDetail(characterId);
    }

    @Override
    public CharacterInfoDto queryByCIdJoinDetail(int cId) {

        CharacterEntity characterEntity = characterDao.queryByCId(cId);
        if(characterEntity == null){
            return  null;
        }
        List<CharacterDetailEntity> characterDetailEntityList = mCharacterDetailDao.queryByCharacterId(characterEntity.getCharacterId());

        CharacterInfoDto characterInfoDto = new CharacterInfoDto();
        characterInfoDto.copy(characterEntity);
        characterInfoDto.setDetails(characterDetailEntityList);
        return characterInfoDto;
    }
}
