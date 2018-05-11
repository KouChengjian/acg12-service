package com.acg12.utils;

import com.acg12.entity.dto.Result;
import com.acg12.conf.Constant;
import com.acg12.entity.po.character.CharacterEntity;
import com.acg12.entity.po.person.PersonEntity;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;


public class StringUtil {


	public static void outputStream(HttpServletResponse response , String content){
		try {
			OutputStream outputStream = response.getOutputStream();
			response.setHeader("content-type", "application/json;charset=UTF-8");
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

	public static synchronized String result(Result result){
		JSONObject json = new JSONObject();
		try{
			json.put("result", result.getResult());
			json.put("desc",   result.getDesc());
			json.put("data",   result.getData());
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


	// 判断字符串是否为数字
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}

	public static String string2Json(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			switch (c){
				case '\"':
					sb.append("\\\"");
					break;
				case '\\':
					sb.append("\\\\");
					break;
				case '/':
					sb.append("\\/");
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\t':
					sb.append("\\t");
					break;
				default:
					sb.append(c);
			}
		}
		return sb.toString();
	}

	public static int randomNum(){
		return (int)((Math.random()*9+1)*1000);
	}

	public static String getJob(String job){
		String typeName = "";
		String[] type = job.split("、");
		for (int i = 0 ; i < type.length ; i++){
			if(i != 0){
				typeName += "、";
			}
			if(type[i].equals("1")){
				typeName += "声优";
			} else if(type[i].equals("2")){
				typeName += "漫画家";
			} else if(type[i].equals("3")){
				typeName += "制作人";
			} else if(type[i].equals("4")){
				typeName += "音乐人";
			} else if(type[i].equals("6")){
				typeName += "演员";
			} else if(type[i].equals("7")){
				typeName += "绘师";
			} else if(type[i].equals("8")){
				typeName += "作家";
			}
		}
		return typeName;
	}

	public static String getPersonInfo(PersonEntity personEntity){
		String s1 = "";
		if(personEntity.getGender() != 0){
			if(personEntity.getGender() == 1){
				s1 += "性别 男";
			} else {
				s1 += "性别 女";
			}
		}
		if(personEntity.getBirthday() != null && !personEntity.getBirthday().isEmpty()){
			s1 += " / 生日 " +personEntity.getBirthday();
		}
		if(personEntity.getBloodtype() == 1){
			s1 += " / 血型 A";
		} else if(personEntity.getBloodtype() == 2){
			s1 += " / 血型 B";
		} else if(personEntity.getBloodtype() == 3){
			s1 += " / 血型 AB";
		} else if(personEntity.getBloodtype() == 4){
			s1 += " / 血型 O";
		}
		if(personEntity.getHeight() != null && !personEntity.getHeight().isEmpty()){
			s1 += " / 身高 " +personEntity.getHeight();
		}
		if(personEntity.getWeight() != null && !personEntity.getWeight().isEmpty()){
			s1 += " / 体重 " +personEntity.getWeight();
		}
		return s1;
	}

	public static String getCharacterInfo(CharacterEntity characterEntity){
		String s1 = "";
		if(characterEntity.getGender() != 0){
			if(characterEntity.getGender() == 1){
				s1 += "性别 男";
			} else {
				s1 += "性别 女";
			}
		}
		if(characterEntity.getBirthday() != null && !characterEntity.getBirthday().isEmpty()){
			s1 += " / 生日 " +characterEntity.getBirthday();
		}
		if(characterEntity.getBloodtype() == 1){
			s1 += " / 血型 A";
		} else if(characterEntity.getBloodtype() == 2){
			s1 += " / 血型 B";
		} else if(characterEntity.getBloodtype() == 3){
			s1 += " / 血型 AB";
		} else if(characterEntity.getBloodtype() == 4){
			s1 += " / 血型 O";
		}
		if(characterEntity.getHeight() != null && !characterEntity.getHeight().isEmpty()){
			s1 += " / 身高 " +characterEntity.getHeight();
		}
		if(characterEntity.getWeight() != null && !characterEntity.getWeight().isEmpty()){
			s1 += " / 体重 " +characterEntity.getWeight();
		}
		return s1;
	}
}
