package com.acg12.modules.app.entity.po.character;

import com.acg12.modules.app.entity.po.Param;

/**
 * Created by Administrator on 2018/4/11.
 */
public class CharacterActorsEntity extends Param{

    private Integer characterActorsId;
    private Integer characterId;
    private Integer personId ;
    private String name;
    private String nameCn;
    private String image;
    private String gender;

    public Integer getCharacterActorsId() {
        return characterActorsId;
    }

    public void setCharacterActorsId(Integer characterActorsId) {
        this.characterActorsId = characterActorsId;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
