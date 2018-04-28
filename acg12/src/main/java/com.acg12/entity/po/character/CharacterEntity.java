package com.acg12.entity.po.character;

import com.acg12.entity.po.Param;

/**
 * Created by Administrator on 2018/4/8.
 */
public class CharacterEntity extends Param {
    private Integer characterId;
    private Integer cId;
    private String name;
    private String nameCn;
    private String image;
    private String height; // 身高
    private String weight; // 体重
    private String alias;  // 别名 （、分割）
    private String type; // 职业 1、角色 2、机体 3、舰船 4、组织机构
    private Integer gender;
    private Integer bloodtype; // 血型  1、A   2、B   3、AB   4、O
    private String birthday; // 生日

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
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

    @Override
    public boolean equals(Object obj) {
        if(toString().hashCode() == obj.toString().hashCode()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "CharacterEntity{" +
                "cId=" + cId +
                ", name='" + name + '\'' +
                ", nameCn='" + nameCn + '\'' +
                ", image='" + image + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", alias='" + alias + '\'' +
                ", type='" + type + '\'' +
                ", gender=" + gender +
                ", bloodtype=" + bloodtype +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
