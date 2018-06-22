package com.acg12.modules.app.entity.dto;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: mayn
 * \* Date: 2018/6/21
 * \* Time: 9:18
 * \* Description:
 * \
 */
public class ListSumDto<T> {

    private T list;
    private int totalResult;

    public ListSumDto(int totalResult, T list) {
        this.totalResult = totalResult;
        this.list = list;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }
}
