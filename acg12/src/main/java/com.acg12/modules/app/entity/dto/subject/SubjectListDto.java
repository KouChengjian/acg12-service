package com.acg12.modules.app.entity.dto.subject;


import com.acg12.modules.app.entity.po.subject.SubjectEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */
public class SubjectListDto {

    // 总记录数
    private int totalResult;
    private List<SubjectEntity> list;

    public SubjectListDto(int totalResult , List<SubjectEntity> list){
        this.totalResult = totalResult;
        this.list = list;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }


    public List<SubjectEntity> getList() {
        return list;
    }

    public void setList(List<SubjectEntity> list) {
        this.list = list;
    }
}
