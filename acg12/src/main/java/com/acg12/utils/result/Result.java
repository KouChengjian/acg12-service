package com.acg12.utils.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public Result() {
        this.success = true;
        this.message = "success";
    }

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public String toString() {
        return JSON.toJSON(this).toString();
    }
}
