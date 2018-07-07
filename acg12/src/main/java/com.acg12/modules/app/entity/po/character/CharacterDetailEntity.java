package com.acg12.modules.app.entity.po.character;

/**
 * Created by Administrator on 2018/4/8.
 */
public class CharacterDetailEntity {

    private Integer characterDetailId;
    private Integer characterId;
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


    @Override
    public String toString() {
        return "CharacterDetailEntity{" +
                "characterDetailId=" + characterDetailId +
                ", characterId=" + characterId +
                ", otherTitle='" + otherTitle + '\'' +
                ", otherValue='" + otherValue + '\'' +
                '}';
    }
}
