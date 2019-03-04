package com.acg12.entity.dto;

import com.acg12.entity.po.Acg12CharacterDetailEntity;
import com.acg12.entity.po.Acg12CharacterEntity;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/12/3 15:09
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Acg12CharacterDto {

    /** id */
    private Integer id;

    /** c_id */
    private Integer cId;

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

    /** 类型 1、角色 2、机体 3、舰船 4、组织机构 */
    private String type;

    /** 性别 1：男 2：女 */
    private Integer gender;

    /** 血型  1、A   2、B   3、AB   4、O */
    private Integer bloodtype;

    /** 生日 */
    private String birthday;

    /** 是否收藏 */
    private Integer isCollect;

    private List<Acg12CharacterDetailEntity> detailList;

    public void copy(Acg12CharacterEntity acg12CharacterEntity) {
        id = acg12CharacterEntity.getId();
        cId = acg12CharacterEntity.getCId();
        name = acg12CharacterEntity.getName();
        nameCn = acg12CharacterEntity.getNameCn();
        image = acg12CharacterEntity.getImage();
        summary = acg12CharacterEntity.getSummary();
        height = acg12CharacterEntity.getHeight();
        weight = acg12CharacterEntity.getWeight();
        alias = acg12CharacterEntity.getAlias();
        type = acg12CharacterEntity.getType();
        gender = acg12CharacterEntity.getGender();
        bloodtype = acg12CharacterEntity.getBloodtype();
        birthday = acg12CharacterEntity.getBirthday();
    }

    public Acg12CharacterEntity chanage() {
        Acg12CharacterEntity characterEntity = new Acg12CharacterEntity();
        characterEntity.setId(this.id);
        characterEntity.setCId(this.cId);
        characterEntity.setName(this.name);
        characterEntity.setNameCn(this.nameCn);
        characterEntity.setImage(this.image);
        characterEntity.setSummary(this.summary);
        characterEntity.setHeight(this.height);
        characterEntity.setWeight(this.weight);
        characterEntity.setAlias(this.alias);
        characterEntity.setType(this.type);
        characterEntity.setGender(this.gender);
        characterEntity.setBloodtype(this.bloodtype);
        characterEntity.setBirthday(this.birthday);
        characterEntity.setCreateTime(new Date());
        characterEntity.setUpdateTime(new Date());
        return characterEntity;
    }

    @Override
    public String toString() {
        return JSON.toJSON(this).toString();
    }
}
