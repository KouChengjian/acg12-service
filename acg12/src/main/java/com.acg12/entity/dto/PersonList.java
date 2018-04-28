package com.acg12.entity.dto;

import com.acg12.entity.po.person.PersonEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
public class PersonList {

    // 总记录数
    private int totalResult;

    private List<PersonEntity> personEntityList;

    public PersonList(int totalResult , List<PersonEntity> personEntityList){
        this.totalResult = totalResult;
        this.personEntityList = personEntityList;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<PersonEntity> getPersonEntityList() {
        return personEntityList;
    }

    public void setPersonEntityList(List<PersonEntity> personEntityList) {
        this.personEntityList = personEntityList;
    }
}
