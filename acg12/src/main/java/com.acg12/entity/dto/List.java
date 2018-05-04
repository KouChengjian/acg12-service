package com.acg12.entity.dto;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/2.
 */
public class List {

    private int totalResult;
    private ArrayList<Object> list;

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public ArrayList<Object> getList() {
        return list;
    }

    public void setList(ArrayList<Object> list) {
        this.list = list;
    }
}
