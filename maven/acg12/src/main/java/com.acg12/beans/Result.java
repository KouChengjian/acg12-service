package com.acg12.beans;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/5/24.
 */
public class Result {

    private int result;
    private String desc;
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


}
