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
    private String roleName;
    private String summary;
    private String image;
    private String weight;
    private String gender;
    private Integer personId ;
    private String personName;
    private String personNameCn;
    private String personImage;
    private String personGender;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonNameCn() {
        return personNameCn;
    }

    public void setPersonNameCn(String personNameCn) {
        this.personNameCn = personNameCn;
    }

    public String getPersonImage() {
        return personImage;
    }

    public void setPersonImage(String personImage) {
        this.personImage = personImage;
    }

    public String getPersonGender() {
        return personGender;
    }

    public void setPersonGender(String personGender) {
        this.personGender = personGender;
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
                ", roleName='" + roleName + '\'' +
                ", summary='" + summary + '\'' +
                ", image='" + image + '\'' +
                ", weight='" + weight + '\'' +
                ", gender='" + gender + '\'' +
                ", personId=" + personId +
                ", personName='" + personName + '\'' +
                ", personNameCn='" + personNameCn + '\'' +
                ", personImage='" + personImage + '\'' +
                ", personGender='" + personGender + '\'' +
                '}';
    }
}
