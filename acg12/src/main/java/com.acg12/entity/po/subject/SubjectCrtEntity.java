package com.acg12.entity.po.subject;

import com.acg12.entity.po.Param;

/**
 * Created by Administrator on 2018/4/11.
 */
public class SubjectCrtEntity extends Param{

    private Integer subjectCrtId;
    private Integer subjectId;
    private Integer characterId ;
    private String name;
    private String nameCn;
    private String roleName;
    private String image;
    private String gender;
    private Integer personId ;
    private String personName;
    private String personNameCn;
    private String personImage;
    private String personGender;

    public Integer getSubjectCrtId() {
        return subjectCrtId;
    }

    public void setSubjectCrtId(Integer subjectCrtId) {
        this.subjectCrtId = subjectCrtId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
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
}
