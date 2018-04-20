package com.acg12.utils.search;

import com.acg12.conf.search.SubjectStaffConstant;
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

    /**
     * @param key
     * @param type  1 书籍 2 动画 3 音乐 4 游戏 6 三次元
     * @param start
     * 书籍包括漫画和小说 分成 7、8
     */
    // 搜索关键字获取列表
    public static synchronized JSONObject getSubjectSearchList(String key, int type, int start) {
//        System.setProperty("http.proxyHost", "localhost");
//        System.setProperty("http.proxyPort", "8888");
//        System.setProperty("https.proxyHost", "localhost");
//        System.setProperty("https.proxyPort", "8888");
        String u = String.format("http://api.bgm.tv/search/subject/%s?type=%s&responseGroup=large&start=%d&max_results=10", UrlEncoderUtil.hasUrlEncoded(key) ? key : UrlEncoderUtil.encode(key), type, start);
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
    }

    // 关键字详情
    public static synchronized JSONObject getSubjectInfo(int subjectid) {
        String url1 = String.format("http://api.bgm.tv/subject/%d?responseGroup=large", subjectid);
        String url2 = String.format("http://bangumi.tv/subject/%d", subjectid);
        String url3 = String.format("http://bangumi.tv/subject/%d/characters", subjectid);
        try {
            Document document = Jsoup.connect(url1).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.body().text();
            if (content == null || content.isEmpty()) {
                return null;
            }

            JSONObject contentJson = new JSONObject(content);
            JSONObject result = new JSONObject();
            result.put("sId", contentJson.getInt("id"));
            result.put("type", contentJson.getInt("type"));
            result.put("name", contentJson.getString("name"));
            result.put("name_cn", contentJson.getString("name_cn"));
            result.put("summary", contentJson.getString("summary"));
            result.put("image", contentJson.getJSONObject("images").getString("large"));
            result.put("eps_count", contentJson.getInt("eps_count"));
            result.put("air_date", contentJson.getString("air_date"));
            result.put("air_weekday", contentJson.getInt("air_weekday"));

            JSONArray detailsJSON = new JSONArray();
            JSONArray crtJSON = new JSONArray();
            JSONArray staffJSON = new JSONArray();
            result.put("details", detailsJSON);
            result.put("crt", crtJSON);
            result.put("staff", staffJSON);

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
                Element item = lis.get(i);
                Elements span = item.select("span");
                String key = span.text().replace(":", "");
                if (ignoreList.contains(key)) {
                    continue;
                }
//                System.out.println(key);

                if (SubjectStaffConstant.animationMap.containsKey(key)) {
                    Elements a = item.select("a");
                    if (a.isEmpty()) {
                        item.select("span").remove();
//                        System.out.println(item.text());
                        JSONObject itemJson = new JSONObject();
                        itemJson.put("otherTitle", key);
                        itemJson.put("otherValue", item.text());
                        detailsJSON.put(itemJson);
                    } else {
                        for (int j = 0, num = a.size(); j < num; j++) {
                            Element element = a.get(j);
                            String pId = element.attr("href").split("person/")[1];
//                            System.out.println(element.text());
//                            System.out.println(pId);
                            JSONObject staff = getPersonInfo(Integer.valueOf(pId).intValue());
                            staff.put("curJob", key);
                            staffJSON.put(staff);
                        }
                    }

                } else {
                    item.select("span").remove();
//                    System.out.println(item.text());
                    JSONObject itemJson = new JSONObject();
                    itemJson.put("otherTitle", key);
                    itemJson.put("otherValue", item.text());
                    detailsJSON.put(itemJson);
                }
            }

            document = Jsoup.connect(url3).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            Element columnInSubjectA = document.getElementById("columnInSubjectA");
            Elements light_odd = columnInSubjectA.getElementsByClass("light_odd");
//            System.err.println(light_odd.size());
            for (int i = 0 , total = light_odd.size() ; i < total ; i++){
                Element item = light_odd.get(i);
                Integer cId = Integer.valueOf(item.getElementsByClass("avatar").select("a").attr("href").split("character/")[1]).intValue();
                String role_name = "";
                Integer pId  = 0;
                Elements clearit = item.getElementsByClass("clearit");
                if(clearit != null && clearit.size() > 0){
                    Elements crt_info = clearit.get(0).getElementsByClass("crt_info");
                    if(crt_info != null && crt_info.size() > 0){
                        Elements badge_job = crt_info.get(0).getElementsByClass("badge_job");
                        if(badge_job != null && badge_job.size() > 0){
                            role_name = badge_job.get(0).text();
                        }
                    }
                    Elements actorBadge = clearit.get(0).getElementsByClass("actorBadge");
                    if(actorBadge != null && actorBadge.size() > 0){
                        pId = Integer.valueOf(actorBadge.select("a").attr("href").split("person/")[1]).intValue();
                    }

                }

//                System.err.println("cId = "+cId);
//                System.err.println("role_name = "+role_name);
//                System.err.println("pId = "+pId);
                JSONObject itemJson =  getCharacterInfo(cId);
                itemJson.put("role_name" , role_name);
                if(pId != 0){
                    itemJson.put("actors",getPersonInfo(pId));
                }


                crtJSON.put(itemJson);

            }
            System.err.println(result.toString());
            return result ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized JSONObject getPersonInfo(int personId) {
        try {
            String url3 = String.format("http://bangumi.tv/person/%d", personId);
            Document document = Jsoup.connect(url3).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();

            JSONObject jsonObject = new JSONObject();
            JSONArray aliasJson = new JSONArray();
            JSONArray otherJson = new JSONArray();
            JSONArray jobJson = new JSONArray();

            Element headerSubject = document.getElementById("headerSubject");
            if (headerSubject.select("h1").select("a").text().isEmpty()){
                return jsonObject;
            }

            Element columnCrtB = document.getElementById("columnCrtB");
            Elements clearit = columnCrtB.getElementsByClass("clearit").select("h2");
            Elements detail = columnCrtB.getElementsByClass("detail");
            Elements center = document.getElementsByClass("infobox");
            String image = center.select("img").attr("src");

            jsonObject.put("pId", personId);
            jsonObject.put("image", image);
            jsonObject.put("name", headerSubject.select("h1").select("a").text());
            jsonObject.put("summary", detail.text());
            jsonObject.put("alias", aliasJson);
            jsonObject.put("other", otherJson);
            jsonObject.put("jobs", jobJson);

            String[] jobs = clearit.text().split(":")[1].split(" ");
            for (int i = 0, num = jobs.length; i < num; i++) {
                if (jobs[i] == null || jobs[i].isEmpty()) {
                    continue;
                }
                jobJson.put(jobs[i]);
            }

            Element infobox = document.getElementById("infobox");
            Elements lis = infobox.select("li");
            for (int i = 0, total = lis.size(); i < total; i++) {
                Element item = lis.get(i);
                Elements span = item.select("span");

                String key = span.text().replace(":", "");
//                System.err.println(key);
                item.select("span").remove();

                if (key.equals("简体中文名")) {
                    jsonObject.put("name_cn", item.text());
                } else if (key.equals("性别")) {
                    jsonObject.put("gender", item.text());
                } else if (key.equals("生日")) {
                    jsonObject.put("birth", item.text());
                } else if (key.equals("血型")) {
                    jsonObject.put("blood_type", item.text());
                } else if (key.equals("体重")) {
                    jsonObject.put("weight", item.text());
                } else if (key.equals("身高")) {
                    jsonObject.put("height", item.text());
                } else if (key.equals("别名")) {
                    aliasJson.put(item.text());
                } else {
                    JSONObject js = new JSONObject();
                    js.put("otherTitle", key);
                    js.put("otherValue", item.text());
                    otherJson.put(js);
                }
            }
//            System.err.println(jsonObject.toString());
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized JSONObject getCharacterInfo(int characterId) {
        try {
            String url3 = String.format("http://bangumi.tv/character/%d", characterId);
            Document document = Jsoup.connect(url3).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();

            JSONObject jsonObject = new JSONObject();
            JSONArray aliasJson = new JSONArray();
            JSONArray otherJson = new JSONArray();

            Element headerSubject = document.getElementById("headerSubject");
            Element columnCrtB = document.getElementById("columnCrtB");
            Elements detail = columnCrtB.getElementsByClass("detail");
            Elements center = document.getElementsByClass("infobox");
            String image = center.select("img").attr("src");

            jsonObject.put("cId", characterId);
            jsonObject.put("image", image);
            jsonObject.put("name", headerSubject.select("h1").select("a").text());
            jsonObject.put("summary", detail.text());
            jsonObject.put("alias", aliasJson);
            jsonObject.put("other", otherJson);

            Element infobox = document.getElementById("infobox");
            Elements lis = infobox.select("li");
            for (int i = 0, total = lis.size(); i < total; i++) {
                Element item = lis.get(i);
                Elements span = item.select("span");

                String key = span.text().replace(":", "");
//                System.err.println(key);
                item.select("span").remove();
                if (key.equals("简体中文名")) {
                    jsonObject.put("name_cn", item.text());
                } else if (key.equals("性别")) {
                    jsonObject.put("gender", item.text());
                } else if (key.equals("生日")) {
                    jsonObject.put("birth", item.text());
                } else if (key.equals("血型")) {
                    jsonObject.put("blood_type", item.text());
                } else if (key.equals("体重")) {
                    jsonObject.put("weight", item.text());
                } else if (key.equals("身高")) {
                    jsonObject.put("height", item.text());
                } else if (key.equals("别名")) {
                    aliasJson.put(item.text());
                } else {
                    JSONObject js = new JSONObject();
                    js.put("otherTitle", key);
                    js.put("otherValue", item.text());
                    otherJson.put(js);
                }
            }

            System.err.println(jsonObject.toString());
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
