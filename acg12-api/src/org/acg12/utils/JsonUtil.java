package org.acg12.utils;

import org.acg12.ResponseStatus;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
	
	public static JSONObject packageJSONObject(ResponseStatus response){
		try {
			JSONObject json = new JSONObject();
			json.put("result", response.getStatusCode());
			json.put("desc",   response.getMsg());
			json.put("data",   response.getData());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject packageJSONObject(String... str  ){
		
		return null;
	}

}
