package com.acg12.utils;

import com.acg12.config.search.SubjectStaffConstant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/9.
 */
public class JsonParse {

    public static String getString(JSONObject json, String key) {
        try {
            if (!json.isNull(key)) {
                return json.getString(key);
            } else
                return "";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getString(JSONArray json, int position) {
        try {
            return json.getString(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getInt(JSONObject json, String key) {
        try {
            if (!json.isNull(key)) {
                return json.getInt(key);
            } else
                return 0;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Double getDouble(JSONObject json, String key) {
        try {
            if (!json.isNull(key)) {
                return json.getDouble(key);
            } else
                return 0.00;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0.00;
    }

    public static JSONArray getJSONArray(JSONObject json, String key) {
        try {
            if (!json.isNull(key)) {
                return json.getJSONArray(key);
            } else
                return new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

    public static JSONObject getJSONObject(JSONArray json, int position) {
        try {
            return json.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getJSONObject(JSONObject json, String key) {
        JSONObject jsonObject = null;
        try {
            if (!json.isNull(key)) {
                jsonObject = json.getJSONObject(key);
            } else {
                jsonObject = new JSONObject();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 业务逻辑
     */

    public static String getJobs(JSONObject json) {
        String name = "";
        JSONArray jsonArray = JsonParse.getJSONArray(json, "staff");
        for (int i = 0, total = jsonArray.length(); i < total; i++) {
            JSONObject item = getJSONObject(jsonArray, i);
//            System.err.println(item.toString());
            if (getString(item, "job").equals("原作")) {
                name += getString(item, "name") + " ";
            }
        }
        return name;
    }

    public static Map<String, String> getStaffs(JSONObject json) {
        JSONArray jsonArray = JsonParse.getJSONArray(json, "staff");
        Map<String, String> map = new HashMap<>();
        Iterator iter = SubjectStaffConstant.animationMap.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String)iter.next();
            String value = "";
            for (int i = 0, total = jsonArray.length(); i < total; i++) {
                JSONObject item = JsonParse.getJSONObject(jsonArray , i);
                if(getString(item, "job").equals(key)){
                    if(value.isEmpty()){
                        value += getString(item, "name");
                    } else {
                        value += " "+getString(item, "name");
                    }

                }
            }
            if(!value.isEmpty()){
                map.put(key , value);
            }
        }
        return map;
    }

}
