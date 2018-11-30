package com.acg12.utils.result;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

public class ReturnJson {
    public static String jsonString(String message, Object data, String status) {
        Map returnMap = new HashMap();
        returnMap.put("message", message);
        returnMap.put("data", data);
        returnMap.put("status", status);
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(returnMap));
        return json.toString();
    }

    public static String jsonString(String message, String status) {
        Map returnMap = new HashMap();
        returnMap.put("message", message);
        returnMap.put("status", status);
        returnMap.put("data", new Object());
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(returnMap));
        return json.toString();
    }


    public static String jsonStringError(String message, String status) {
        Map returnMap = new HashMap();
        returnMap.put("message", message);
        returnMap.put("status", status);
        returnMap.put("data", new Object());
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(returnMap));
        return json.toString();
    }

    public static String jsonStringOk() {
        Map returnMap = new HashMap();
        returnMap.put("message", "OK");
        returnMap.put("status", AppConstants.OK);
        returnMap.put("data", new Object());
        String result = JSONObject.toJSONString(returnMap,
                SerializerFeature.WriteMapNullValue);
        return result;
    }

    public static String jsonStringOk(Object data) {
        Map returnMap = new HashMap();
        returnMap.put("message", "OK");
        returnMap.put("data", data);
        returnMap.put("status", AppConstants.OK);
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(returnMap));
        return json.toString();
    }

    public static String jsonStringOk(Object data, String message) {
        Map returnMap = new HashMap();
        returnMap.put("message", message);
        returnMap.put("data", data);
        returnMap.put("status", AppConstants.OK);
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(returnMap));
        return json.toString();
    }

    public static String jsonStringError(String message) {
        Map returnMap = new HashMap();
        returnMap.put("message", message);
        returnMap.put("status", AppConstants.AppError5000000);
        returnMap.put("data", new Object());
        JSONObject json = JSONObject.parseObject(JSON.toJSONString(returnMap));
        return json.toString();
    }
}
