package com.acg12.conf;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Condition { 
	
	public static Map<String,Object> create(String s1,String s2){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("code", s1);
		hashmap.put("msg", s2);
		return hashmap;
	}
	
	public static Map<String,Object> create(String s1,String s2,Map<String,Object> map){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("code", s1);
		hashmap.put("msg", s2);
		hashmap.put("data", map);
		return hashmap;
	}
	
	public static Map<String,Object> create(String s1,String s2,List<Object> list){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("code", s1);
		hashmap.put("msg", s2);
		hashmap.put("data", list);
		return hashmap;
	} 
	
	public static Map<String,Object> create109(){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("code", 10099);
		hashmap.put("msg", "请先登录");
		return hashmap;
	} 
	
	public static Map<String,Object> create200(){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("code", 20000);
		hashmap.put("msg", "执行成功");
		return hashmap;
	}
	
	public static Map<String,Object> create200(Object obj){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("code", 20000);
		hashmap.put("msg", "请求成功");
		hashmap.put("data", obj);
		return hashmap;
	} 
	
	public static Map<String,Object> create200(Map<String,Object> map){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("code", 20000);
		hashmap.put("msg", "请求成功");
		hashmap.put("data", map);
		return hashmap;
	} 
	
	public static Map<String,Object> create200(Map<String,Object> map,HashMap<String,Object> m){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		hashmap.put("code", 20000);
		hashmap.put("msg", "请求成功");
		if (m.get("total") != null) {
			map2.put("total",m.get("total"));
		}
		if (m.get("page") != null) {
			map2.put("page",(int)m.get("page")+1);
		}
		if (m.get("rows") != null) {
			map2.put("rows",m.get("rows"));
		}
		map2.put("data", map);
		hashmap.put("data", map2);
		return hashmap;
	} 
	
	public static Map<String,Object> create200(Map<String,Object> map,Map<String,Object> m){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		hashmap.put("code", 20000);
		hashmap.put("msg", "请求成功");
		if (m.get("total") != null) {
			map2.put("total",m.get("total"));
		}
		if (m.get("page") != null) {
			map2.put("page", (int)m.get("page")+1);
		}
		if (m.get("rows") != null) {
			map2.put("rows", m.get("rows"));
		}
		map2.put("data", map);
		hashmap.put("data", map2);
		return hashmap;
	} 
	
	public static Map<String,Object> create200(List<?> list){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("code", 20000);
		hashmap.put("msg", "请求成功");
		hashmap.put("data", list);
		return hashmap;
	} 
	
	public static Map<String,Object> create200(List<?> list,HashMap<String,Object> m){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		hashmap.put("code", 20000);
		hashmap.put("msg", "请求成功");
		if (m.get("total") != null) {
			map2.put("total", m.get("total"));
		}
		if (m.get("page") != null) {
			map2.put("page", (int)m.get("page")+1);
		}
		if (m.get("rows") != null) {
			map2.put("rows", m.get("rows"));
		}
		map2.put("data", list);
		hashmap.put("data", map2);
		return hashmap;
	} 
	
	public static Map<String,Object> create200(List<?> list,Map<String,Object> m){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		hashmap.put("code", 20000);
		hashmap.put("msg", "请求成功");
		if (m.get("total") != null) {
			map2.put("total", m.get("total"));
		}
		if (m.get("page") != null) {
			map2.put("page", (int)m.get("page")+1);
		}
		if (m.get("rows") != null) {
			map2.put("rows", m.get("rows"));
		}
		map2.put("data", list);
		hashmap.put("data", map2);
		return hashmap;
	}
	
	public static Map<String,Object> create202(){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("code", 20002);
		hashmap.put("msg", "所查数据不存在");
		return hashmap;
	} 
	
	public static Map<String,Object> create206(){
		Map<String,Object> hashmap = new HashMap<String,Object>();
		hashmap.put("code", 20006);
		hashmap.put("msg", "程序运行时报错");
		return hashmap;
	} 

}
