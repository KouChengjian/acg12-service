package com.acg12.modules.app.entity.dto;

public class ListDto<T> {

    private  T list;

    public ListDto(T list){
        this.list = list;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
