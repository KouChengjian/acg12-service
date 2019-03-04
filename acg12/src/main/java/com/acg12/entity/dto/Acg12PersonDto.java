package com.acg12.entity.dto;

import com.acg12.entity.po.Acg12PersonDetailEntity;
import com.acg12.entity.po.Acg12PersonEntity;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/12/1 9:47
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Acg12PersonDto {

    /** id */
    private Integer id;

    /** pId */
    private Integer pId;

    /** 名称 */
    private String name;

    /** 中文名称 */
    private String nameCn;

    /** 图片 */
    private String image;

    /** 简介 */
    private String summary;

    /** 身高 */
    private String height;

    /** 体重 */
    private String weight;

    /** 别名 */
    private String alias;

    /**  1、声优 2、漫画家 3、制作人 4、音乐人 5、 6、演员 7、绘师 8、作家 */
    private String type;

    /** // 1、男 2、女 */
    private String gender;

    /** 血型  1、A   2、B   3、AB   4、O */
    private Integer bloodtype;

    /** 生日 */
    private String birthday;

    /** 是否收藏 */
    private Integer isCollect;

    private List<Acg12PersonDetailEntity> detailList;

    public void copy(Acg12PersonEntity acg12PersonEntity) {
        id = acg12PersonEntity.getId();
        pId = acg12PersonEntity.getPId();
        name = acg12PersonEntity.getName();
        nameCn = acg12PersonEntity.getNameCn();
        image = acg12PersonEntity.getImage();
        summary = acg12PersonEntity.getSummary();
        height = acg12PersonEntity.getHeight();
        weight = acg12PersonEntity.getWeight();
        alias = acg12PersonEntity.getAlias();
        type = acg12PersonEntity.getType();
        gender = acg12PersonEntity.getGender();
        bloodtype = acg12PersonEntity.getBloodtype();
        birthday = acg12PersonEntity.getBirthday();
    }

    public Acg12PersonEntity chanage() {
        Acg12PersonEntity personEntity = new Acg12PersonEntity();
        personEntity.setId(this.id);
        personEntity.setPId(this.pId);
        personEntity.setName(this.name);
        personEntity.setNameCn(this.nameCn);
        personEntity.setImage(this.image);
        personEntity.setSummary(this.summary);
        personEntity.setHeight(this.height);
        personEntity.setWeight(this.weight);
        personEntity.setAlias(this.alias);
        personEntity.setType(this.type);
        personEntity.setGender(this.gender);
        personEntity.setBloodtype(this.bloodtype);
        personEntity.setBirthday(this.birthday);
        personEntity.setCreateTime(new Date());
        personEntity.setUpdateTime(new Date());
        return personEntity;
    }

    @Override
    public String toString() {
        return JSON.toJSON(this).toString();
    }
}
