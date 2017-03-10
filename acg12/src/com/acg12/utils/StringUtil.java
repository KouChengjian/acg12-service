package com.acg12.utils;

import com.acg12.config.Constant;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletResponse;



public class StringUtil {


	public static void outputStream(HttpServletResponse response , String content){
		try {
			OutputStream outputStream = response.getOutputStream();
			response.setHeader("content-type", "text/html;charset=UTF-8");
			byte[] dataByteArr = content.getBytes("UTF-8");
			outputStream.write(dataByteArr);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized String result(JSONObject content){
		JSONObject json = new JSONObject();
		try{
			if(content == null ){
				json.put("result", 201);
				json.put("desc",   "获取失败");
				json.put("data",   new JSONObject());
			} else {
				json.put("result", 200);
				json.put("desc",   "获取成功");
				json.put("data",   content);
			}
		} catch (JSONException e){

		}

		return json.toString();
	}
	
	public static String getMoreVideoUrl(String type){
		String url = "";
		if(type.equals("all-7-33")){ // 排行
			url = Constant.URL_RANK_BANGUMI;
		}else if(type.equals("all-7-1")){
			url = Constant.URL_RANK_DOUGA;
		}else if(type.equals("all-7-3")){
			url = Constant.URL_RANK_MUSIC;
		}else if(type.equals("all-7-5")){
			url = Constant.URL_RANK_ENT;
		}else if(type.equals("all-7-119")){
			url = Constant.URL_RANK_KICHIKU;
		}else if(type.equals("default-33")){ // 番剧
			url = Constant.URL_BANKUN_SERIALIZE;
		}else if(type.equals("default-32")){
			url = Constant.URL_BANKUN_END;
		}else if(type.equals("default-51")){
			url = Constant.URL_BANKUN_MESSAGE;
		}else if(type.equals("default-152")){
			url = Constant.URL_BANKUN_OFFICIAL;
		}else if(type.equals("default-153")){
			url = Constant.URL_BANKUN_DOMESTIC;
		}else if(type.equals("default-24")){ // 动漫
			url = Constant.URL_DONGMAN_MAD_AMV;
		}else if(type.equals("default-25")){
			url = Constant.URL_DONGMAN_MMD_3D;
		}else if(type.equals("default-47")){
			url = Constant.URL_DONGMAN_SHORT_FILM;
		}else if(type.equals("default-27")){
			url = Constant.URL_DONGMAN_SYNTHESIZE;
		}
		return url;
	}
	
	/**
	 * 压缩获取数据
	 * @param inStream
	 * @param charsetName
	 * @return
	 * @throws Exception
	 */
	public static String readDataForZgip(InputStream inStream,
            String charsetName) throws Exception {
        GZIPInputStream gzipStream = new GZIPInputStream(inStream);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = gzipStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        gzipStream.close();
        inStream.close();
        return new String(data, charsetName);
    }
	
	public static String readDataForZgip(InputStream inStream) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(inStream,"UTF-8"));  
        StringBuffer resultBuffer = new StringBuffer();  
        String tempLine = null;  
        while ((tempLine = br.readLine()) != null) {  
            resultBuffer.append(tempLine);  
        }  
        return resultBuffer.toString();
	}
	
	
}
