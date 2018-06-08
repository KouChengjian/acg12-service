package com.acg12.common.utils.stack;

/**
 * Created by Administrator on 2018/5/14.
 */
public class IndexStack {

    private int position;
    private Object object;

    public  IndexStack(int position , Object object){
        this.position = position;
        this.object = object;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
