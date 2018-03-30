package com.acg12.utils.search;

import com.acg12.entity.po.SubjectEntity;
import com.acg12.utils.UrlEncoderUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */
public class SearchUtli {

    // 搜索关键字获取列表
    public static synchronized JSONObject getSubjectSearchList(String key, int type, int start) {
//        System.setProperty("http.proxyHost", "localhost");
//        System.setProperty("http.proxyPort", "8888");
//        System.setProperty("https.proxyHost", "localhost");
//        System.setProperty("https.proxyPort", "8888");
        String u = String.format("http://api.bgm.tv/search/subject/%s?type=%d&responseGroup=large&start=%d&max_results=10", UrlEncoderUtil.hasUrlEncoded(key) ? key : UrlEncoderUtil.encode(key), type, start);
        try {
            Document document = Jsoup.connect(u).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();

            JSONObject jsonObject = new JSONObject(document.body().text());
            JSONArray list = jsonObject.getJSONArray("list");
            for (int i = 0, total = list.length(); i < total; i++) {
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

    // 关键字详情
    public static synchronized void getSubjectInfo(int subjectid) {
        String url1 = String.format("http://api.bgm.tv/subject/%d?responseGroup=large", subjectid);
        String url2 = String.format("http://bangumi.tv/subject/%d", subjectid);

        try {
            Document document = Jsoup.connect(url1).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();

            String content = document.body().text();
            if (content == null || content.isEmpty()) {
                return;
            }
            JSONObject result = new JSONObject(content);
            SubjectEntity subjectEntity = new SubjectEntity();
            subjectEntity.setsId(result.getInt("id"));
            subjectEntity.setType(result.getInt("type"));
            subjectEntity.setName(result.getString("name"));
            subjectEntity.setNameCn(result.getString("name_cn"));
            subjectEntity.setSummary(result.getString("summary"));
            subjectEntity.setImage(result.getJSONObject("images").getString("large"));
            subjectEntity.setEpsCount(result.getInt("eps_count"));
            subjectEntity.setAirDate(result.getString("air_date"));
            subjectEntity.setAirWeekday(result.getInt("air_weekday"));
            System.err.println(subjectEntity.toString());

            List<String> ignoreList = new ArrayList<>();
            ignoreList.add("中文名");
            ignoreList.add("话数");
            ignoreList.add("放送开始");

            document = Jsoup.connect(url2).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();

            Element infobox = document.getElementById("infobox");
            Elements lis = infobox.select("li");
            for (int i = 0, total = lis.size(); i < total; i++) {
                String key, value , link = null;
                Element item = lis.get(i);
                Elements span = item.select("span");

                key = span.text().replace(":", "");
                if (ignoreList.contains(key)) {
                    continue;
                }
                System.err.println(key);
                Elements a = item.select("a");
                if (a.isEmpty()) {
                    item.select("span").remove();
                    System.err.println(item.text());
                } else {
                    for (int j = 0 , num = a.size() ; j < num ; j++){
                        Element element = a.get(j);
                        System.err.println(element.text());
                        System.err.println(element.attr("href"));

                    }
                }
            }
//            System.err.println(infobox.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void getPersonInfo(int personId){
        try {
            String url3 = String.format("http://bangumi.tv/person/%s", personId);
            Document document = Jsoup.connect(url3).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            System.err.println(document.body());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
