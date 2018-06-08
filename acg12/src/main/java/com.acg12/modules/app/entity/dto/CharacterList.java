package com.acg12.modules.app.entity.dto;


import com.acg12.modules.app.entity.po.character.CharacterEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
public class CharacterList {

    private int totalResult;
    private List<CharacterEntity> characterEntitieList;

    public CharacterList(int totalResult , List<CharacterEntity> characterEntitieList){
        this.totalResult = totalResult;
        this.characterEntitieList = characterEntitieList;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<CharacterEntity> getCharacterEntitieList() {
        return characterEntitieList;
    }

    public void setCharacterEntitieList(List<CharacterEntity> characterEntitieList) {
        this.characterEntitieList = characterEntitieList;
    }
}
