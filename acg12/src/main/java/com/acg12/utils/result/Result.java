package com.acg12.utils.result;

import com.acg12.constant.AppConstants;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new R.
     */
    public Result() {
        put("code", AppConstants.OK);
        put("msg", "success");
    }

    /**
     * Error r.
     *
     * @return the r
     */
    public static Result error() {
        return error(AppConstants.AppError5000000, "未知异常，请联系管理员");
    }

    /**
     * Error r.
     *
     * @param msg the msg
     * @return the r
     */
    public static Result error(String msg) {
        return error(AppConstants.AppError5000000, msg);
    }

    /**
     * Error r.
     *
     * @param code the code
     * @param msg  the msg
     * @return the r
     */
    public static Result error(int code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    /**
     * Error r.
     *
     * @param msg  the msg
     * @param code the code
     * @return the r
     */
    public static Result error(String msg, String code) {
        Result r = new Result();
        r.put("msg", msg);
        r.put("code", code);
        return r;
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Object o) {
        return new Result().put("data", o);
    }

    public static Result ok(List list) {
        return new Result().put("data", list);
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
