package com.acg12.entity.po;

/**
 * Created by Administrator on 2018/4/8.
 */
public class CharacterDetailEntity {

    private Integer characterDetailId;
    private Integer characterId;
    private String alias;
    private String otherTitle;
    private String otherValue;

    public Integer getCharacterDetailId() {
        return characterDetailId;
    }

    public void setCharacterDetailId(Integer characterDetailId) {
        this.characterDetailId = characterDetailId;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getOtherTitle() {
        return otherTitle;
    }

    public void setOtherTitle(String otherTitle) {
        this.otherTitle = otherTitle;
    }

    public String getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }
}
