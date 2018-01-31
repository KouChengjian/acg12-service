package com.acg12.entity.dto;

import com.acg12.conf.Constant;
import com.acg12.utils.MD5Util;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
public class Result {

    // 返回json
    private int result = -1;
    private String desc = "未初始化";
    private JSONObject data = new JSONObject();

    public Result() {

    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public void putDataObject(String key, Object object) {
        try {
            Gson gson = new Gson();
            JSONObject json = new JSONObject(gson.toJson(object));
            data.put(key, json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putDataArray(String key, List object) {
        try {
            Gson gson = new Gson();
            JSONArray json = new JSONArray(gson.toJson(object));
            data.put(key, json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putDataObject(String key, JSONObject object) {
        try {
            data.put(key, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putDataArray(String key, JSONArray object) {
        try {
            data.put(key, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void write(HttpServletResponse response) {
        JSONObject json = new JSONObject();
        try {
            json.put("result", getResult());
            json.put("desc", getDesc());
            json.put("data", getData());
            OutputStream outputStream = response.getOutputStream();
            response.setHeader("content-type", "application/json;charset=UTF-8");
            byte[] dataByteArr = json.toString().getBytes("UTF-8");
            outputStream.write(dataByteArr);
            outputStream.flush();
            outputStream.close();
        } catch (JSONException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** --------------------------------------------成功object----------------------------------------------- */
    public void writeSucceed(HttpServletResponse response) {
        writeSucceed("成功",response);
    }

    public void writeSucceed(String desc,HttpServletResponse response) {
        setResult(Constant.HTTP_RESULT_SUCCEED);
        setDesc(desc);
        write(response);
    }

    public void writeSucceed(String key, Object object, HttpServletResponse response) {
        writeSucceed("成功" , key , object, response);
    }

    public void writeSucceed(String desc, String key, Object object, HttpServletResponse response) {
        writeSucceed(Constant.HTTP_RESULT_SUCCEED , desc, key , object, response);
    }

    public void writeSucceed(int result, String desc, String key, Object object, HttpServletResponse response) {
        setResult(result);
        setDesc(desc);
        putDataObject(key, object);
        write(response);
    }

    /** --------------------------------------------成功objectList----------------------------------------------- */
    public void writeSucceed(String key, List object, HttpServletResponse response) {
        writeSucceed("成功" , key , object, response);
    }

    public void writeSucceed(String desc, String key, List object, HttpServletResponse response) {
        writeSucceed(Constant.HTTP_RESULT_SUCCEED , desc, key , object, response);
    }

    public void writeSucceed(int result, String desc, String key, List list, HttpServletResponse response) {
        setResult(result);
        setDesc(desc);
        putDataArray(key, list);
        write(response);
    }

    /** --------------------------------------------成功JSONObject----------------------------------------------- */
    public void writeSucceed(JSONObject object, HttpServletResponse response) {
        writeSucceed("list" , object, response);
    }

    public void writeSucceed(String key , JSONObject object, HttpServletResponse response) {
        writeSucceed("成功" , key , object, response);
    }

    public void writeSucceed(String desc , String key , JSONObject object, HttpServletResponse response) {
        writeSucceed(Constant.HTTP_RESULT_SUCCEED ,desc , key , object, response);
    }

    public void writeSucceed(int result, String desc, String key, JSONObject object, HttpServletResponse response) {
        setResult(result);
        setDesc(desc);
        putDataObject(key, object);
        write(response);
    }

    /** --------------------------------------------成功JSONArray----------------------------------------------- */
    public void writeSucceed(JSONArray object, HttpServletResponse response) {
        writeSucceed("list" , object, response);
    }

    public void writeSucceed(String key , JSONArray object, HttpServletResponse response) {
        writeSucceed("成功" , key , object, response);
    }

    public void writeSucceed(String desc , String key , JSONArray object, HttpServletResponse response) {
        writeSucceed(Constant.HTTP_RESULT_SUCCEED ,desc , key , object, response);
    }

    public void writeSucceed(int result, String desc, String key, JSONArray object, HttpServletResponse response) {
        setResult(result);
        setDesc(desc);
        putDataArray(key, object);
        write(response);
    }

    /** --------------------------------------------失败----------------------------------------------- */
    public void writeFailure( HttpServletResponse response) {
        writeFailure("操作失败" , response);
    }

    public void writeFailure(String desc, HttpServletResponse response) {
        writeFailure(Constant.HTTP_RESULT_ERROR , desc , response);
    }

    public void writeFailure(int result, String desc, HttpServletResponse response) {
        setResult(result);
        setDesc(desc);
        write(response);
    }


    public boolean verifySign(HttpServletRequest request) {
        String u = request.getHeader("u");
        String p = request.getHeader("p"); // 手机系统平台（1:安卓 2:ios 3:未知）
        String s = request.getHeader("s"); // 随机字符串（未获取到默认'unknown'）
        String n = request.getHeader("n"); // 手机名称（未获取到默认'unknown'）
        String d = request.getHeader("d"); // 手机设备ID（未获取到默认'unknown'）
        String v = request.getHeader("v"); // 手机系统版本号（未获取到默认'unknown'）
        String a = request.getHeader("a"); // app系统版本号（未获取到默认'unknown'）
        String t = request.getHeader("t"); // 时间戳（未获取到默认'unknown'）
        String g = request.getHeader("g"); // 签名（未获取到默认'unknown'）
//        System.err.println("u = " + u +" p = " + p + "  s = " + s + "  n = " + n + "  d = " + d + "  v = " + v + "  a = " + a + "  t = " + t + "  g = " + g);
        String sign = MD5Util.encryption(p + s + n + d + v + a + t + u + Constant.USER_KEY);
        if(g == null){
            return true;
        }
        if(g.equals(sign)){
            return true;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
