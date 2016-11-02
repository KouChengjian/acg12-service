package org.acg12.utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acg12.Constant;
import org.json.JSONArray;
import org.json.JSONObject;


public class ReptileUtils {

	public static String getHomeContent(HttpServletRequest request,HttpServletResponse response){
		final String bannerContent  = HttpUtlis.getBanner();
		final String albumContent   = HttpUtlis.getAlbumHtmlString("");
        final String paletteContent = HttpUtlis.getPaletteHtmlString("");
        final String videoContent   = HttpUtlis.getHomeHtmlString();
        
        JSONObject json = new JSONObject();
		
		try {
			if(bannerContent.isEmpty() && albumContent.isEmpty() && 
	        		paletteContent.isEmpty() && videoContent.isEmpty()){
	        	json.put("result", Constant.ERROR.getStatusCode()+"");
	    		json.put("desc",   Constant.ERROR.getMsg());
	    		json.put("data",   "");
	        	StringUtil.weiteData(json.toString(),response);
	        } else {
	        	JSONObject array = new JSONObject();
	        	JSONArray bannerJson = new JSONArray(bannerContent);
				JSONArray albumJson = new JSONArray(albumContent);
				JSONArray paletteJson = new JSONArray(paletteContent);
				JSONArray videoJson = new JSONArray(videoContent);
				
				array.put("banner",bannerJson);
				array.put("album",albumJson);
				array.put("palette",paletteJson);
				
				for(int i = 0 ; i < videoJson.length() ; i++){
					JSONArray video = videoJson.getJSONArray(i);
					if(i == 0){
						array.put("bangumi",video);
					}else if(i == 1){
						array.put("douga",video);
					}
				}
	        	
	        	json.put("result", Constant.SUCCESS.getStatusCode()+"");
	    		json.put("desc",   Constant.SUCCESS.getMsg());
	    		json.put("data",   array.toString());
	        	StringUtil.weiteData(json.toString(),response);
	        }
		} catch (Exception e) {
			System.out.println(e.toString());
		}
        return json.toString();
	} 
	
	public static String getFindContent(HttpServletRequest request,HttpServletResponse response){
		String page = request.getParameter("page");
		final String findContent  = HttpUtlis.getFind(page);
		if(findContent != null && !findContent.isEmpty()){
			return StringUtil.weiteData(findContent,Constant.SUCCESS,response);
		} else {
			return StringUtil.weiteData(findContent,Constant.ERROR,response);
		}
	}
	
	public static void getSearchAlbum(HttpServletRequest request,HttpServletResponse response){
		String key = request.getParameter("key");
		String page = request.getParameter("page");
		final String content = HttpUtlis.getSearchAlbum(key, page);
		if(content != null && !content.isEmpty()){
			StringUtil.weiteData(content,Constant.SUCCESS,response);
		} else {
			StringUtil.weiteData(content,Constant.ERROR,response);
		}	
	}
	
	public static void getSearchPalette(HttpServletRequest request,HttpServletResponse response){
		String key = request.getParameter("key");
		String page = request.getParameter("page");
		final String content = HttpUtlis.getSearchPalette(key, page);
		if(content != null && !content.isEmpty()){
			StringUtil.weiteData(content,Constant.SUCCESS,response);
		} else {
			StringUtil.weiteData(content,Constant.ERROR,response);
		}	
	}
	
	public static void getSearchVideo(HttpServletRequest request,HttpServletResponse response){
		String key = request.getParameter("key");
		String page = request.getParameter("page");
		final String content = HttpUtlis.getSearchVideo(key, page);
		if(content != null && !content.isEmpty()){
			StringUtil.weiteData(content,Constant.SUCCESS,response);
		} else {
			StringUtil.weiteData(content,Constant.ERROR,response);
		}	
	}
	
	public static void getSearchBangunmi(HttpServletRequest request,HttpServletResponse response){
		String key = request.getParameter("key");
		String page = request.getParameter("page");
		final String content = HttpUtlis.getSearchBangunmi(key, page);
		if(content != null && !content.isEmpty()){
			StringUtil.weiteData(content,Constant.SUCCESS,response);
		} else {
			StringUtil.weiteData(content,Constant.ERROR,response);
		}	
	}
}
