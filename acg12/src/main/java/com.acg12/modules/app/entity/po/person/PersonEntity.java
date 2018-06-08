package com.acg12.modules.app.entity.po.person;

import com.acg12.modules.app.entity.po.Param;

/**
 * Created by Administrator on 2018/4/8.
 */
public class PersonEntity extends Param {
    private Integer personId;
    private Integer pId;
    private String name;
    private String nameCn;
    private String image;
    private String height; // 身高
    private String weight; // 体重
    private String alias;  // 别名 （、分割）
    private String type; // 职业 1、声优 2、漫画家 3、制作人 4、音乐人 5、 6、演员 7、绘师 8、作家
    private Integer gender;  // 性别 // 1、男 2、女
    private Integer bloodtype; // 血型  1、A   2、B   3、AB   4、O
    private String birthday; // 生日
    // 额外数据
    private String summary;

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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
        if (toString().hashCode() == obj.toString().hashCode()) {
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
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", alias='" + alias + '\'' +
                ", type='" + type + '\'' +
                ", gender=" + gender +
                ", bloodtype=" + bloodtype +
                ", birthday='" + birthday + '\'' +
                '}';
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
