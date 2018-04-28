package com.acg12.entity.po.character;

/**
 * Created by Administrator on 2018/4/8.
 */
public class CharacterDetailEntity {

    private Integer characterDetailId;
    private Integer characterId;
    private String summary;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
