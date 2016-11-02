package org.acg12.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.acg12.Constant;
import org.acg12.bean.Album;
import org.acg12.bean.Palette;
import org.acg12.bean.Video;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;




public class HttpUtlis {
	
	/**
	 * 首页-横幅
	 */
	public static synchronized String getBanner() {
		String content = "";
		List<Video> bannerList = new ArrayList<Video>();
		try {
			URL url = new URL(Constant.URL_HOME_BRAND);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			InputStream inStream = conn.getInputStream();
			if ("gzip".equals(conn.getContentEncoding())) {
	        	content = StringUtil.readDataForZgip(inStream, "utf-8");
	        }else{
	        	content = StringUtil.readDataForZgip(inStream);
	        }
	        conn.disconnect();
	        if(content != null && !content.isEmpty()){
	        	JSONObject bannerjson = new JSONObject(content);
	        	JSONArray array = bannerjson.getJSONArray("result");
				for (int i=0 , num = array.length() ; i < num ; i++) {
					Video item = new Video();		
					item.setPic(array.getJSONObject(i).getString("img").toString());
					item.setTitle(array.getJSONObject(i).getString("title").toString());
					item.setUrlInfo(array.getJSONObject(i).getString("link").toString());
					bannerList.add(item);
				}
				Gson gson = new Gson();
				content = gson.toJson(bannerList);
				//System.out.println(content);
	        }else {
	        	content = "";
	        }
		} catch (MalformedURLException e) {
			e.printStackTrace();
			content = "";
		} catch (ProtocolException e) {
			e.printStackTrace();
			content = "";
		} catch (IOException e) {
			e.printStackTrace();
			content = "";
		} catch (Exception e) {
			e.printStackTrace();
			content = "";
		}
        return content;
	}
	
	/**
	 * 首页-获取新的画集
	 */
	public static synchronized String getAlbumHtmlString(String max ) {
		String content = "";
		List<Album> albumList = new ArrayList<Album>();
		try {
			Document document = Jsoup.connect(Constant.URL_ALBUM + "&max=" + max)
					.data("jquery", "java").userAgent("Mozilla")
					.cookie("auth", "token").timeout(50000).get();
			if (document.toString() == null || document.toString().isEmpty()) {
				
			}else{
				Elements var = document.body().select("script");
				for (Element div : var) {
					String str1 = "app.page[" + "\"" + "ads" + "\"" + "] = ";
					String str2 = "app.page[" + "\"" + "pins" + "\"" + "] = ";
					String str3 = StringUtils.substringBetween(div.toString(),str2, str1);
					if (str3 != null && !str3.isEmpty()) {
						JSONArray array = new JSONArray(str3);
						for (int i = 0; i < array.length(); i++) {
							JSONObject json = (JSONObject) array.get(i);
							JSONObject jsomUrl = (JSONObject) json.get("file");
							ArrayList<String> url = new ArrayList<String>();
							int width = 0, height = 0;
							width = 720 / 2 - 30;
							height = width * jsomUrl.getInt("height")
									/ jsomUrl.getInt("width");
							if (height > 500) {
								height = 500;
							}
							Album album = new Album();
							album.setContent(json.getString("raw_text"));
							album.setPinId(String.valueOf(json.getInt("pin_id")));
							url.add("http://img.hb.aicdn.com/"
									+ jsomUrl.getString("key") + "_fw658");
							album.setUrlList(url);
							album.setResWidth(width);
							album.setResHight(height);
							album.setLove(json.getInt("like_count"));
							album.setFavorites(json.getInt("repin_count"));
							albumList.add(album);
						}
					}
				}
				Gson gson = new Gson();
				content = gson.toJson(albumList);
				//System.out.println(content);
			}
		} catch (IOException e) {
			e.printStackTrace();
			content = "";
		} catch (JSONException e) {
			e.printStackTrace();
			content = "";
		}
		return content;
	}

	/**
	 * 首页-获取画板
	 */
	public static synchronized String getPaletteHtmlString(String max) {
		String content = "";
		List<Palette> paletteList = new ArrayList<Palette>();
		try {
			Document document = Jsoup
					.connect(Constant.URL_PALETTE + "&max=" + max)
					.data("jquery", "java").userAgent("Mozilla")
					.cookie("auth", "token").timeout(50000).get();
			if (document.toString() == null || document.toString().isEmpty()) {
			}else{
				Elements var = document.body().select("script");
				for (Element div : var) {
					String str1 = "app.page[" + "\"" + "boards" + "\"" + "] = ";
					String str2 = "app.page[" + "\"" + "promotions" + "\"" + "] = ";
					String str3 = StringUtils.substringBetween(div.toString(),str1, str2);
					if (str3 != null && !str3.isEmpty()) {
						JSONArray json = new JSONArray(str3);
						for (int i = 0; i < json.length(); i++) {
							Palette palette = new Palette();
							ArrayList<String> url = new ArrayList<String>();
							JSONObject boardjs = (JSONObject) json.get(i);
							JSONArray array = boardjs.getJSONArray("pins");
							for (int j = 0; j < array.length(); j++) {
								JSONObject js = (JSONObject) array.get(j);
								JSONObject jsfile = js.getJSONObject("file");
								url.add("http://img.hb.aicdn.com/"
										+ jsfile.getString("key") + "_fw658");
							}
							palette.setName(boardjs.getString("title"));
							palette.setNum(boardjs.getInt("pin_count"));
							palette.setUrlAlbum(url);
							palette.setBoardId(boardjs.getString("board_id"));
							paletteList.add(palette);
						}
					}
				}
				Gson gson = new Gson();
				content = gson.toJson(paletteList);
				//System.out.println(content);
			}
		} catch (IOException e) {
			e.printStackTrace();
			content = "";
		} catch (JSONException e) {
			e.printStackTrace();
			content = "";
		}
		return content;
	}
	
	/**
	 * 主页-内容
	 */
	public static synchronized String getHomeHtmlString(){
		String content = "";
		List<List<Video>> videoLl = new ArrayList<List<Video>>();
		try {
			URL url = new URL(Constant.URL_HOME_CONTENT);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10 * 1000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != 200)
	            throw new RuntimeException("请求url失败");
	        InputStream is = conn.getInputStream();//拿到输入流  
	        if ("gzip".equals(conn.getContentEncoding())) {
	        	content = StringUtil.readDataForZgip(is, "utf-8");
	        }else{
	        	content = StringUtil.readDataForZgip(is);
	        }
	        conn.disconnect();
	        if(content == null || content.isEmpty()){
	        	
	        }else{
	        	ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
	        	JSONObject bangumijson  = new JSONObject(content);
	        	JSONObject bangumiarray = bangumijson.getJSONObject("bangumi");// 新番
				JSONObject dougaarray   = bangumijson.getJSONObject("douga");//动画
//				JSONObject musicarray   = bangumijson.getJSONObject("music"); //音乐
//				JSONObject kichikuarray = bangumijson.getJSONObject("kichiku"); //鬼畜
//				JSONObject entarray     = bangumijson.getJSONObject("ent"); //娱乐
				jsonList.add(bangumiarray);
				jsonList.add(dougaarray);
//				jsonList.add(musicarray);
//				jsonList.add(kichikuarray);
//				jsonList.add(entarray);
				for (int j=0 ; j < jsonList.size() ; j++) {
					ArrayList<Video> videoList = new ArrayList<Video>();
					for (int i = 0 ; i < 10 ; i++) {
						Video item = new Video();		
						item.        setAid(jsonList.get(j).getJSONObject(i+"").getString("aid").toString());
						item.     setTypeid(jsonList.get(j).getJSONObject(i+"").getString("typeid").toString());
						item.      setTitle(jsonList.get(j).getJSONObject(i+"").getString("title").toString());
						item.   setSbutitle(jsonList.get(j).getJSONObject(i+"").optString("sbutitle").toString());
						item.       setPlay(jsonList.get(j).getJSONObject(i+"").getString("play").toString());
						item.     setReview(jsonList.get(j).getJSONObject(i+"").getString("review").toString());
						item.setVideoReview(jsonList.get(j).getJSONObject(i+"").getString("video_review").toString());
						item.  setFavorites(jsonList.get(j).getJSONObject(i+"").getString("favorites").toString());
						item.        setMid(jsonList.get(j).getJSONObject(i+"").getString("mid").toString());
						item.     setAuthor(jsonList.get(j).getJSONObject(i+"").getString("author").toString());
						item.setDescription(jsonList.get(j).getJSONObject(i+"").getString("description").toString());
						item.     setCreate(jsonList.get(j).getJSONObject(i+"").getString("create").toString());
						item.        setPic(jsonList.get(j).getJSONObject(i+"").getString("pic").toString());
						item.     setCredit(jsonList.get(j).getJSONObject(i+"").getString("credit").toString());
						item.      setCoins(jsonList.get(j).getJSONObject(i+"").getString("coins").toString());
						item.   setDuration(jsonList.get(j).getJSONObject(i+"").getString("duration").toString());	
						videoList.add(item);
					}
					videoLl.add(videoList);
				}
				Gson gson = new Gson();
				content = gson.toJson(videoLl);
				System.out.println(content);
	        }
		} catch (MalformedURLException e) {
			e.printStackTrace();
			content = "";
		} catch (ProtocolException e) {
			e.printStackTrace();
			content = "";
		} catch (IOException e) {
			e.printStackTrace();
			content = "";
		} catch (JSONException e) {
			e.printStackTrace();
			content = "";
		} catch (Exception e) {
			e.printStackTrace();
			content = "";
		}
		return content ;
	}
	
	
	
	
	
	
	
	
	/**
	 * 发现 - 番剧
	 */
	public static synchronized String getFind(String page){
		String content = "";
		final List<Video> videoList = new ArrayList<Video>();
		try {
			URL url = new URL(Constant.URL_FIND_BANKUN + page);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10 * 1000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != 200)
	            throw new RuntimeException("请求url失败");
	        InputStream is = conn.getInputStream();//拿到输入流  
	        if ("gzip".equals(conn.getContentEncoding())) {
	        	content = StringUtil.readDataForZgip(is, "utf-8");
	        }else{
	        	content = StringUtil.readDataForZgip(is);
	        }
	        conn.disconnect();
	        if(content != null && !content.isEmpty()){
	        	JSONObject json = new JSONObject(content);
				JSONObject result = json.getJSONObject("result");
				JSONArray list = result.getJSONArray("list");
				for(int i = 0 ; i < list.length() ; i++){
					JSONObject jsonObject =  list.getJSONObject(i);
					Video item = new Video();
					item.setUrlInfo(jsonObject.getString("url"));
					item.setPic(jsonObject.getString("cover"));
					item.setTitle(jsonObject.getString("title"));
					item.setUpdateContent("更新至"+jsonObject.getString("newest_ep_index")+"话");
					videoList.add(item);
				}
	        	Gson gson = new Gson();
				content = gson.toJson(videoList);
				//System.out.println(content);
	        }else{
	        	content = "";
	        }
		} catch (MalformedURLException e) {
			e.printStackTrace();
			content = "";
		} catch (IOException e) {
			e.printStackTrace();
			content = "";
		} catch (Exception e) {
			content = "";
			e.printStackTrace();
		}
		return content;
	}
	
	
	/**
	 * 搜索-图片
	 */
	public static synchronized String getSearchAlbum(String key , String page ){
		String content = "";
		List<Album> albumList = new ArrayList<Album>();
		try {
			Document document = Jsoup
					.connect(Constant.URL_SEARCH_ALBUM + URLEncoder.encode(key, "UTF-8") +"&page="+page)
					.data("jquery", "java").userAgent("Mozilla")
					.cookie("auth", "token").timeout(50000).get();
			if(document.toString() == null || document.toString().isEmpty()){
				
			}else{
				Elements var = document.body().select("script");
				//System.out.println(var.toString());
				for (Element div : var) {
					String str1 = "app.page[" + "\"" + "pins" + "\"" + "] = ";
					//System.out.println(str1);
					String str2 = "app.page[" + "\"" + "page" + "\"" + "] = ";
					//System.out.println(str2);
					String str3 = StringUtils.substringBetween(div.toString(),str1, str2);
					//System.out.println(str3);
					if (str3 != null && !str3.isEmpty()) {
						JSONArray array = new JSONArray(str3);
						for (int i = 0; i < array.length(); i++) {
							JSONObject json = (JSONObject) array.get(i);
							JSONObject jsomUrl = (JSONObject) json.get("file");
							ArrayList<String> url = new ArrayList<String>();
							int width = 0, height = 0;
							width = 720 / 2 - 30;
							height = width * jsomUrl.getInt("height")
									/ jsomUrl.getInt("width");
							if (height > 500) {
								height = 500;
							}
							Album album = new Album();
							album.setContent(json.getString("raw_text"));
							album.setPinId(String.valueOf(json.getInt("pin_id")));
							url.add("http://img.hb.aicdn.com/"
									+ jsomUrl.getString("key") + "_fw658");
							album.setUrlList(url);
							album.setResWidth(width);
							album.setResHight(height);
							album.setLove(json.getInt("like_count"));
							album.setFavorites(json.getInt("repin_count"));
							albumList.add(album);
						}
					}
				}
				Gson gson = new Gson();
				content = gson.toJson(albumList);
				System.out.println(content);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	/**
	 * 搜索-画板
	 */
	public static synchronized String getSearchPalette(String key , String page ) {
		String content = "";
		List<Palette> paletteList = new ArrayList<Palette>();
		try {
			Document document = Jsoup
					.connect(Constant.URL_SEARCH_PALETTE + URLEncoder.encode(key, "UTF-8") +"&page="+page)
					.data("jquery", "java").userAgent("Mozilla")
					.cookie("auth", "token").timeout(50000).get();
			if(document.toString() == null || document.toString().isEmpty()){
			}else{
				Elements var = document.body().select("script");
				//System.out.println(var.toString());
				for (Element div : var) {
					String str1 = "app.page[" + "\"" + "boards" + "\"" + "] = ";
					String str2 = "app._csr = true";
					String str3 = StringUtils.substringBetween(div.toString(),str1, str2);
					if (str3 != null && !str3.isEmpty()) {
						JSONArray json = new JSONArray(str3);
						for (int i = 0; i < json.length(); i++) {
							Palette palette = new Palette();
							ArrayList<String> url = new ArrayList<String>();
							JSONObject boardjs = (JSONObject) json.get(i);
							JSONArray array = boardjs.getJSONArray("pins");
							for (int j = 0; j < array.length(); j++) {
								JSONObject js = (JSONObject) array.get(j);
								JSONObject jsfile = js.getJSONObject("file");
								url.add("http://img.hb.aicdn.com/"
										+ jsfile.getString("key") + "_fw658");
							}
							palette.setName(boardjs.getString("title"));
							palette.setNum(boardjs.getInt("pin_count"));
							palette.setUrlAlbum(url);
							palette.setBoardId(boardjs.getString("board_id"));
							paletteList.add(palette);
						}
					}
				}
				Gson gson = new Gson();
				content = gson.toJson(paletteList);
				System.out.println(content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	/**
	 * 搜索-视频
	 */
    public static synchronized String getSearchVideo(String key , String page ){
    	String content = "";
		List<Video> videoList = new ArrayList<Video>();
		try {
			//System.out.println(Constant.URL_SEARCH_VIDEO + "&keyword="+URLEncoder.encode(key, "UTF-8")+"&page="+page);
			Document document = Jsoup.connect(Constant.URL_SEARCH_VIDEO + "&keyword="+URLEncoder.encode(key, "UTF-8")+"&page="+page).data("jquery", "java")
					.userAgent("Mozilla").cookie("auth", "token")
					.timeout(50000).get();
			if (document.toString() == null || document.toString().isEmpty()) {
				
			}else{
				//System.out.println(document.toString());
				Elements so_wrap = document.getElementsByClass("ajax-render");
				//System.out.println(so_wrap.toString());
				Elements li = so_wrap.select("li");
				//System.out.println(li.size());
				for(int i = 0 ; i < li.size() ; i++){
					Element item = li.get(i); 
					Video video = new Video();
					Elements img = item.select("a[href]");
					//Elements intro = item.select("p");
					Elements icon_wrap = item.getElementsByClass("tags");
					Elements span = icon_wrap.select("span");
					//System.out.println(item.toString());
					//System.out.println(icon_wrap.toString());
					//System.out.println("------------");
					video.setAid(img.attr("href").split("av")[1].replace("/",""));
					//System.out.println("setAid==="+img.attr("href").split("av")[1].replace("/",""));
					video.setTitle(img.attr("title"));
					//System.out.println("title==="+img.attr("title"));
					video.setPic(img.select("[src]").attr("abs:src"));
					//System.out.println("src==="+img.select("[src]").attr("abs:src"));
					video.setDescription("");
					//System.out.println("setDescription==="+intro.text());
					video.setFavorites("");
					//System.out.println();
					video.setPlay(span.get(0).text());
					//System.out.println(span.get(0).text());
					video.setVideoReview(span.get(1).text());
					//System.out.println(span.get(1).text());
					video.setCreate(span.get(2).text());
					//System.out.println(span.get(2).text());
				    video.setAuthor(span.get(3).text());
				    //System.out.println(span.get(3).text());
					videoList.add(video);
				}
				Gson gson = new Gson();
				content = gson.toJson(videoList);
				System.out.println(content);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		} 
		return content;
	}
	
	/**
	 * 搜索-番剧
	 */
    public static synchronized String getSearchBangunmi(String key ,String page ){
    	String content = "";
		List<Video> videoList = new ArrayList<Video>();
		try {
			Document document = Jsoup.connect(Constant.URL_SEARCH_SERIES + "&keyword="+URLEncoder.encode(key, "UTF-8")+"&page="+page).data("jquery", "java")
					.userAgent("Mozilla").cookie("auth", "token")
					.timeout(50000).get();
			if (document.toString() == null || document.toString().isEmpty()) {
				
			}else{
				Elements ajax_render = document.select("div.ajax-render");
				Elements left_img = document.select("div.left-img");
				Elements img = left_img.select("a");
				String imgurl = img.select("[src]").attr("abs:src");
				String title = img.attr("title");
				//System.out.println(imgurl);
				//System.out.println(title);
				Elements right_info = document.select("div.right-info"); 
				Elements div_des = right_info.select("div.des"); 
				String des = div_des.text();
				//System.out.println(des);
				Elements so_episode = ajax_render.select("ul.so-episode"); 
				//System.out.println(so_episode.toString());
				Elements list = so_episode.select("a"); 
				//System.out.println(list.size());
				for(int i = 0 ; i < list.size() ; i++){
					Video video = new Video();
					Element item = list.get(i);
					String videourl = item.attr("href");
					String quarter = item.text();
					//System.out.println(videourl);
					//System.out.println(quarter);
					video.setUrlInfo(videourl);
					video.setPic(imgurl);
					video.setTitle(title+quarter);
					video.setDescription(des);
					video.setUpdateContent("");
					
					videoList.add(video);
				}
				Gson gson = new Gson();
				content = gson.toJson(videoList);
				//System.out.println(content);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		} 
		return content;
	}
	
}
