package com.acg12.utils;

import com.acg12.constant.SubjectStaffConstant;
import com.acg12.utils.checkoutjson.IndexStack;
import com.acg12.utils.checkoutjson.MyStack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by Administrator on 2018/4/9.
 */
public class JsonParse {

    public static JSONObject stringToJson(String string) {
        try {
            return new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

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

    public static String checkJson(String s) {
        MyStack<IndexStack> stack = new MyStack<IndexStack>();
        List<Integer> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
//            System.out.println("i = " + i);
            char c = s.charAt(i);
//            System.out.println(c);
            switch (c) {
                case '\"':
//                    System.out.println("入栈");
                    stack.push(new IndexStack(i , c));
                    sb.append(c);
                    break;
                case ':':
                    if(String.valueOf(s.charAt(i-1)).equals("\"")){
//                        System.out.println("出栈 = stack.size() = "+stack.size());
                        if (stack.size() != 0) {
                            if (stack.size() == 2) {
                                stack.pop();
                                stack.pop();
                            } else if (stack.size() > 2) {
//                                System.out.println("数据异常大于2");
                            } else if (stack.size() < 2) {
//                                System.out.println("数据异常小于2");
                            }
                        }
//                        System.out.println("出栈 = stack.size() = "+stack.size());
                    }
                    sb.append(c);
                    break;
                case ',': // 目前不能识别段落里面的英文逗号
                    int num = stack.size();
                    for (int k = 0  ; k < num ; k++){
                        IndexStack indexStack = (IndexStack)stack.pop();
//                        System.out.println("indexStack.getPosition() = "+indexStack.getPosition());
//                        System.out.println("k = "+ k);
                        if(k == 0 || k == num - 1){

                        } else {
//                            System.out.println("执行");
//                            sb.replace(indexStack.getPosition() , indexStack.getPosition() +1 ,"&quot;");
                            list.add(indexStack.getPosition());
//                            System.out.println(sb.toString());
                        }
                    }
                    sb.append(c);
                    break;
                default:
                    sb.append(c);
            }
//            System.out.println("lenght = " +sb.length());
        }
//        System.out.println("list = " +list.size());
        Collections.sort(list);
        for (int i = list.size() -1 ; i >=  0 ; i--){
//            System.out.println(list.get(i));
            sb.replace(list.get(i) , list.get(i) +1 ,"&quot;");
        }
//        System.out.println(sb.toString());
        return sb.toString();
    }

}
