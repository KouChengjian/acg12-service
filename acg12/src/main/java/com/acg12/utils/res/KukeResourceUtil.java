package com.acg12.utils.res;

import com.acg12.entity.dto.Acg12CaricatureChaptersDto;
import com.acg12.entity.dto.Acg12CaricatureChaptersInfoDto;
import com.acg12.entity.dto.Acg12CaricatureDto;
import com.acg12.utils.JsonParse;
import com.acg12.utils.UrlEncoderUtil;
import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/12/28 16:27
 * Description: 酷克漫画
 */
public class KukeResourceUtil {
    final static String sign = "package_name=com.vjson.anime&version_code=87&version_name=1.0.8.7&channel=coolapk&sign=dcf692dc1d4cead44ce1d5d1b9409e26&platform=android";

    // http://api.cookacg.com/comics?q[name_cont]=小埋&page=1
    // http://api.cookacg.com/comics/1?package_name=com.vjson.anime&version_code=87&version_name=1.0.8.7&channel=coolapk&sign=dcf692dc1d4cead44ce1d5d1b9409e26&platform=android
    // http://api.cookacg.com/comics/1/2?package_name=com.vjson.anime&version_code=87&version_name=1.0.8.7&channel=coolapk&sign=dcf692dc1d4cead44ce1d5d1b9409e26&platform=android

    public static synchronized List<Acg12CaricatureDto> kukeSearch(String key) {
        String url = String.format("http://api.cookacg.com/comics?q[name_cont]=%s&page=1&", UrlEncoderUtil.hasUrlEncoded(key) ? key : UrlEncoderUtil.encode(key))  + sign;
//        System.out.println(url);
        try {
            OkHttpClient okHttpClient = new OkHttpClient(); // 创建OkHttpClient对象
            Request request = new Request.Builder().url(url).build(); // 创建一个请求
            Response response = okHttpClient.newCall(request).execute(); // 返回实体
            if (!response.isSuccessful()) { // 判断是否成功
                System.out.println("失败"); // 链接失败
                return null;
            }
            String content = response.body().string();
//            System.out.println(content);
            JSONObject object = new JSONObject(content);
            JSONArray entries = JsonParse.getJSONArray(object, "entries");
            List<Acg12CaricatureDto> caricatureDtoList = new ArrayList<>();
            for (int i = 0; i < entries.length(); i++) {
                JSONObject item = JsonParse.getJSONObject(entries, i);
//                System.out.println(item.toString());
                Acg12CaricatureDto caricatureDto = new Acg12CaricatureDto();
                caricatureDto.setComicId(JsonParse.getInt(item, "comic_id"));
                caricatureDto.setType(1);
                caricatureDto.setCover(JsonParse.getString(item, "cover"));
                caricatureDto.setTitle(JsonParse.getString(item, "name"));
                caricatureDtoList.add(caricatureDto);
            }
            System.out.println(JSON.toJSON(caricatureDtoList));
            return caricatureDtoList;
        } catch (Exception e) {
//            System.out.println(e.toString());
        }
        return null;
    }

    public static synchronized Acg12CaricatureDto kukeCaricatureInfo(int id) {
        String url = "http://api.cookacg.com/comics/" + id + "?" + sign;
        try {
            OkHttpClient okHttpClient = new OkHttpClient(); // 创建OkHttpClient对象
            Request request = new Request.Builder().url(url).build(); // 创建一个请求
            Response response = okHttpClient.newCall(request).execute(); // 返回实体
            if (!response.isSuccessful()) { // 判断是否成功
                System.out.println("失败"); // 链接失败
                return null;
            }
            String content = response.body().string();
//            System.out.println(content);
            JSONObject object = new JSONObject(content);
            Acg12CaricatureDto caricatureDto = new Acg12CaricatureDto();
            caricatureDto.setComicId(JsonParse.getInt(object, "id"));
            caricatureDto.setType(1);
            caricatureDto.setCover(JsonParse.getString(object, "cover"));
            caricatureDto.setTitle(JsonParse.getString(object, "name"));

            JSONArray entries = JsonParse.getJSONArray(object, "chapters");
            List<Acg12CaricatureChaptersDto> chapterList = new ArrayList<>();
            for (int i = 0; i < entries.length(); i++) {
                JSONObject item = JsonParse.getJSONObject(entries, i);
//                System.out.println(item.toString());
                Acg12CaricatureChaptersDto chaptersDto = new Acg12CaricatureChaptersDto();
                chaptersDto.setComicId(JsonParse.getInt(item, "comic_id"));
                chaptersDto.setIndex(JsonParse.getInt(item, "index"));
                chaptersDto.setTitle(JsonParse.getString(item, "name"));
                chapterList.add(chaptersDto);
            }
            caricatureDto.setChaptersList(chapterList);
            System.out.println(JSON.toJSON(caricatureDto));
            return caricatureDto;
        } catch (Exception e) {
//            System.out.println(e.toString());
        }
        return null;
    }

    public static synchronized Acg12CaricatureChaptersDto kukeCaricatureChapters(int id, int index) {
        String url = "http://api.cookacg.com/comics/" + id + "/" + index + "?" + sign;
        try {
            OkHttpClient okHttpClient = new OkHttpClient(); // 创建OkHttpClient对象
            Request request = new Request.Builder().url(url).build(); // 创建一个请求
            Response response = okHttpClient.newCall(request).execute(); // 返回实体
            if (!response.isSuccessful()) { // 判断是否成功
                System.out.println("失败"); // 链接失败
                return null;
            }
            String content = response.body().string();
//            System.out.println(content);
            JSONObject object = new JSONObject(content);
            Acg12CaricatureChaptersDto caricatureDto = new Acg12CaricatureChaptersDto();
            caricatureDto.setComicId(JsonParse.getInt(object, "id"));
            caricatureDto.setTitle(JsonParse.getString(object, "name"));
            caricatureDto.setIndex(JsonParse.getInt(object, "index"));

            JSONArray entries = JsonParse.getJSONArray(object, "pages");
            List<Acg12CaricatureChaptersInfoDto> chapterList = new ArrayList<>();
            for (int i = 0; i < entries.length(); i++) {
                JSONObject item = JsonParse.getJSONObject(entries, i);
//                System.out.println(item.toString());
                Acg12CaricatureChaptersInfoDto chaptersDto = new Acg12CaricatureChaptersInfoDto();
                chaptersDto.setUrl(JsonParse.getString(item, "track_url"));
                chaptersDto.setSort(i);
                chapterList.add(chaptersDto);
            }
            caricatureDto.setPags(chapterList);
            System.out.println(JSON.toJSON(caricatureDto));
            return caricatureDto;
        } catch (Exception e) {
//            System.out.println(e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        kukeSearch("火影");
//        kukeCaricatureInfo(18657);
//        kukeCaricatureChapters(18657 , 1);
    }
}
