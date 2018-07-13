package com.acg12.modules.app.entity.dto.subject;

import com.acg12.modules.app.entity.po.character.CharacterDetailEntity;
import com.acg12.modules.app.entity.po.character.CharacterEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/7/10 10:25
 * Description:
 */
public class CharacterInfoDto {
    private Integer subjectId;
    private Integer sId;
    private String name;
    private String nameCn;
    private String image;
    private String summary;
    private String height; // 身高
    private String weight; // 体重
    private String alias;  // 别名 （、分割）
    private String type; // 职业 1、角色 2、机体 3、舰船 4、组织机构
    private Integer gender;
    private Integer bloodtype; // 血型  1、A   2、B   3、AB   4、O
    private String birthday; // 生日
    private List<CharacterDetailEntity> details = new ArrayList<>();

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer characterId) {
        this.subjectId = characterId;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer cId) {
        this.sId = cId;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(Integer bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<CharacterDetailEntity> getDetails() {
        return details;
    }

    public void setDetails(List<CharacterDetailEntity> characterDetailEntityList) {
        this.details = characterDetailEntityList;
    }

    public void copy(CharacterEntity characterEntity) {
        subjectId = characterEntity.getCharacterId();
        sId = characterEntity.getcId();
        name = characterEntity.getName();
        nameCn = characterEntity.getNameCn();
        image = characterEntity.getImage();
        summary = characterEntity.getSummary();
        height = characterEntity.getHeight();
        weight = characterEntity.getWeight();
        alias = characterEntity.getAlias();
        type = characterEntity.getType();
        gender = characterEntity.getGender();
        bloodtype = characterEntity.getBloodtype();
        birthday = characterEntity.getBirthday();
    }
}
