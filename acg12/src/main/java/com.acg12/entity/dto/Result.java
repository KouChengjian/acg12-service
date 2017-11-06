package com.acg12.entity.dto;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
public class Result {

    private int result = -1;
    private String desc = "未初始化";
    private JSONObject data = new JSONObject();

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

    public void putDataObject(String key , Object object) {
        try {
            Gson gson = new Gson();
            JSONObject json = new JSONObject(gson.toJson(object));
            data.put(key , json);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putDataArray(String key , List object) {
        try {
            Gson gson = new Gson();
            JSONArray json = new JSONArray(gson.toJson(object));
            data.put(key , json);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void write(HttpServletResponse response){
        JSONObject json = new JSONObject();
        try{
            json.put("result", getResult());
            json.put("desc",   getDesc());
            json.put("data",   getData());
            OutputStream outputStream = response.getOutputStream();
            response.setHeader("content-type", "application/json;charset=UTF-8");
            byte[] dataByteArr = json.toString().getBytes("UTF-8");
            outputStream.write(dataByteArr);
            outputStream.flush();
            outputStream.close();
        } catch (JSONException e){

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
