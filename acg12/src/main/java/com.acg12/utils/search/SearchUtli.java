package com.acg12.utils.search;

import com.acg12.entity.po.SubjectEntity;
import com.acg12.utils.StringUtil;
import com.acg12.utils.UrlEncoderUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */
public class SearchUtli {

    // 搜索关键字获取列表
    public static synchronized JSONObject getSubjectSearchList(String key , int type ,int start){
        String u = String.format("http://api.bgm.tv/search/subject/%s?type=%d&responseGroup=large&start=%d&max_results=10" , UrlEncoderUtil.hasUrlEncoded(key) ? key : UrlEncoderUtil.encode(key), type , start);
        try {
            Document document = Jsoup.connect(u).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();

            JSONObject jsonObject = new JSONObject(document.body().text());
            JSONArray list= jsonObject.getJSONArray("list");
            for (int i = 0 , total = list.length() ; i < total ; i++) {
                JSONObject item = list.getJSONObject(i);
                item.remove("rating");
                item.remove("rank");
                item.remove("collection");
            }
            System.err.println(jsonObject.toString());
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        /*System.err.println(u);
        try {
            Document document = Jsoup.connect(u).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            System.err.println(document.toString());

            Element elementList = document.getElementById("browserItemList");
            Elements elements = elementList.select("li");

            List<SubjectEntity> entityList = new ArrayList<>();
            for (Element element : elements){
                SubjectEntity subjectEntity = new SubjectEntity();
                subjectEntity.setsId(Integer.valueOf(element.select("a").get(0).attr("href").split("/")[2]).intValue());
                subjectEntity.setImage(element.select("img").get(0).attr("src").replace("s/" , "l/"));
                subjectEntity.setName(element.select("h3").get(0).select("small").text());
                subjectEntity.setName_cn(element.select("h3").get(0).select("a").text());
                subjectEntity.setOtherParam(element.select("p").get(0).text());
                entityList.add(subjectEntity);
                System.err.println(element.select("a").get(0).attr("href").split("/")[2]);
                System.err.println(element.select("img").get(0).attr("src"));
                System.err.println(element.select("h3").get(0).select("a").text());
                System.err.println(element.select("h3").get(0).select("small").text());
                System.err.println(element.select("p").get(0).text());
            }
            return entityList;
        } catch (IOException e) {
            return null;
        }*/

    }

    public static synchronized void getSubjectSearch11(){
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "8888");
        try {
            URL url = new URL("http://api.bgm.tv/search/subject/%E5%A4%8F%E5%A8%9C?type=2&responseGroup=large&start=0");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-agent","Mozilla/4.0");
//            conn.setRequestProperty("Cookie", "buvid3=C4C5E5DA-AA7A-488F-90B6-1D9F6630601B12546infoc");
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200)
                throw new RuntimeException("请求url失败  " +conn.getResponseCode());
            InputStream is = conn.getInputStream();//拿到输入流
            String s = "";
            if ("gzip".equals(conn.getContentEncoding())) {
                s = StringUtil.readDataForZgip(is, "utf-8");
            } else {
                s = StringUtil.readDataForZgip(is);
            }
            conn.disconnect();
            System.err.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    // 关键字详情
    public static synchronized void getSubjectInfo(int subjectid ){
        String url = String.format("https://api.bgm.tv/subject/%d?responseGroup=large" , subjectid );
        try {
            Document document = Jsoup.connect(url).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.toString();
            System.err.println(document.body().text());



        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
