package com.acg12.entity.po.person;

import com.acg12.entity.po.Param;

/**
 * Created by Administrator on 2018/4/8.
 */
public class PersonEntity extends Param {
    private Integer personId ;
    private Integer pId ;
    private String name;
    private String nameCn;
    private String image;
    private String summary;
    private String gender;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        return "PersonEntity{" +
                "pId=" + pId +
                ", name='" + name + '\'' +
                ", nameCn='" + nameCn + '\'' +
                ", image='" + image + '\'' +
                ", summary='" + summary + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
