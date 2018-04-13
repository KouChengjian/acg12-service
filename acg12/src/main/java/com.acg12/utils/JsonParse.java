package com.acg12.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/4/9.
 */
public class JsonParse {

    public static String getString(JSONObject json , String key){
        try {
            if(!json.isNull(key)){
                return json.getString(key);
            }else
                return "";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getString(JSONArray json , int position){
        try {
            return json.getString(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getInt(JSONObject json , String key){
        try {
            if(!json.isNull(key)){
                return json.getInt(key);
            }else
                return 0;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Double getDouble(JSONObject json , String key){
        try {
            if(!json.isNull(key)){
                return json.getDouble(key);
            }else
                return 0.00;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0.00;
    }

    public static JSONArray getJSONArray(JSONObject json , String key){
        try {
            if(!json.isNull(key)){
                return json.getJSONArray(key);
            }else
                return new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

    public static JSONObject getJSONObject(JSONArray json , int position){
        try {
            return json.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getJSONObject(JSONObject json , String key){
        JSONObject jsonObject = null;
        try {
            if(!json.isNull(key)){
                jsonObject = json.getJSONObject(key);
            } else {
                jsonObject = new JSONObject();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
