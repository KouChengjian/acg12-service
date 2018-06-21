package com.acg12.common.utils.crawler;

import com.acg12.config.SubjectStaffConstant;
import com.acg12.common.utils.JsonParse;
import com.acg12.common.utils.StringUtil;
import com.acg12.common.utils.UrlEncoderUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */
public class BgmCrawler {

    /**
     * @param key
     * @param type  1 书籍 2 动画 3 音乐 4 游戏 6 三次元
     * @param start 书籍包括漫画和小说 分成 7、8
     */
    // 搜索关键字获取列表
    public static synchronized JSONObject getBgmSearchSubjectList(String key, int type, int start) {
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

    public static synchronized JSONObject getBgmSearchPresonList(String key) {
        String url = String.format("http://bangumi.tv/mono_search/%s?cat=all", UrlEncoderUtil.hasUrlEncoded(key) ? key : UrlEncoderUtil.encode(key));
        try {
            System.out.println(url);
            Document document = Jsoup.connect(url).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            System.out.println(document.body());
            Element columnSearchB= document.getElementById("columnSearchB");
//            System.out.println(columnSearchB.toString());
        } catch (Exception e) {

        }
        return null;
    }

    public static synchronized JSONObject getSubjectInfoSimple(int subjectid) {
//        System.setProperty("http.proxyHost", "localhost");
//        System.setProperty("http.proxyPort", "8888");
//        System.setProperty("https.proxyHost", "localhost");
//        System.setProperty("https.proxyPort", "8888");
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
//            System.out.println(content);
//            System.out.println(StringUtil.stringToJson(content));
            content = StringUtil.stringToJson(content);
            JSONObject contentJson = new JSONObject(content);
            if (!contentJson.isNull("code")) {
                return null;
            }
            JSONObject result = new JSONObject();
            int type = JsonParse.getInt(contentJson, "type");
            result.put("sId", JsonParse.getInt(contentJson, "id"));
            result.put("type", JsonParse.getInt(contentJson, "type"));
            result.put("name", JsonParse.getString(contentJson, "name"));
            result.put("name_cn", JsonParse.getString(contentJson, "name_cn"));
            result.put("summary", JsonParse.getString(contentJson, "summary"));
            result.put("image", contentJson.getJSONObject("images").getString("large"));
            result.put("eps_count", JsonParse.getInt(contentJson, "eps_count"));
            result.put("air_date", JsonParse.getString(contentJson, "air_date"));
            result.put("air_weekday", JsonParse.getInt(contentJson, "air_weekday"));
            JSONArray detailsJSON = new JSONArray();
            JSONArray crtJSON = new JSONArray();
            JSONArray staffJSON = new JSONArray();
            JSONArray offprintJSON = new JSONArray();
            JSONArray songJSON = new JSONArray();
            result.put("details", detailsJSON);
            result.put("crt", crtJSON);
            result.put("staff", staffJSON);

            List<String> ignoreList = new ArrayList<>();
            ignoreList.add("中文名");
            ignoreList.add("话数");
            ignoreList.add("集数");
            ignoreList.add("放送开始"); // 动画
            ignoreList.add("发售日"); // 书籍
            ignoreList.add("发行日期"); // 游戏
            ignoreList.add("发售日期"); // 音乐
            ignoreList.add("开始");

            document = Jsoup.connect(url2).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();

            Element headerSubject = document.getElementById("headerSubject");
            if (headerSubject == null) {
                return null;
            }

            HashMap<String, String> hashMap = null;
            if (type == 1) {
                hashMap = SubjectStaffConstant.booksMap;
                String typeName = headerSubject.select("h1").select("small").text();
                if (!typeName.isEmpty()) {
                    result.put("type_name", typeName);
                }
            } else if (type == 2) {
                hashMap = SubjectStaffConstant.animationMap;
                String typeName = headerSubject.select("h1").select("small").text();
                if (!typeName.isEmpty()) {
                    result.put("type_name", typeName);
                }
            } else if (type == 3) {
                hashMap = SubjectStaffConstant.musicMap;
                result.put("type_name", "音乐");
            } else if (type == 4) {
                hashMap = SubjectStaffConstant.gameMap;
            } else if (type == 6) {
                hashMap = SubjectStaffConstant.realMap;
            } else {
                return null;
            }

            Element infobox = document.getElementById("infobox");
            Elements lis = infobox.select("li");
            for (int i = 0, total = lis.size(); i < total; i++) {
                Element item = lis.get(i);
                Elements span = item.select("span");
                String key = span.text().replace(":", "");
                if (ignoreList.contains(key)) {
                    if (result.getString("air_date").equals("0000-00-00")) {
                        if (key.contains("放送开始") || key.contains("发售日") || key.contains("开始")) {
                            item.select("span").remove();
                            result.put("air_date", item.text());
                        }
                    }
                    continue;
                }

                if (hashMap.containsKey(key)) {
                    Elements a = item.select("a");
                    if (a.isEmpty()) {
                        item.select("span").remove();
//                        System.out.println(item.text());
                        JSONObject itemJson = new JSONObject();
                        itemJson.put("name", item.text());
                        itemJson.put("curJob", key);
                        staffJSON.put(itemJson);
                    } else {
                        for (int j = 0, num = a.size(); j < num; j++) {
                            Element element = a.get(j);
                            String pId = element.attr("href").split("person/")[1];
                            JSONObject staff = new JSONObject();
                            staff.put("pId", pId);
                            staff.put("name", element.text());
                            staff.put("curJob", key);
                            staffJSON.put(staff);
                        }
                    }
                } else {
                    item.select("span").remove();
                    if (type == 1) {
                    } else if (type == 2) {
                    } else if (type == 3) {
                    } else if (type == 4) {
                        if (key.contains("游戏类型")) {
                            result.put("type_name", item.text());
                            continue;
                        }
                    } else if (type == 6) {
                        if (key.contains("类型")) {
                            result.put("type_name", item.text().replace(" / ", ","));
                            continue;
                        }
                    }
                    if (key.contains("结束")) {
                        result.put("end_date", item.text());
                        continue;
                    } else {
                        JSONObject itemJson = new JSONObject();
                        itemJson.put("otherTitle", key);
                        itemJson.put("otherValue", item.text());
                        detailsJSON.put(itemJson);
                    }
                }
            }

            if (type == 1) {
                result.put("offprint", offprintJSON);
                Elements browserCoverSmall = document.getElementsByClass("browserCoverSmall");
                Elements browserList = browserCoverSmall.select("li");
                for (int k = 0, num = browserList.size(); k < num; k++) {
                    Element element = browserList.get(k);
                    String id = null;
                    String name;
                    String pic;
                    String aitem = element.select("a").attr("href");
                    if (aitem != null) {
                        id = aitem.split("subject/")[1];
                    }
                    name = element.select("a").attr("title");
                    pic = element.select("span").attr("style").replace("background-image:url('", "").replace("')", "");
                    JSONObject j = new JSONObject();
                    j.put("sId", id);
                    j.put("name", name);
                    j.put("pic", pic);
                    offprintJSON.put(j);
                }
            } else if (type == 3) {
                result.put("song", songJSON);
                Elements subject_ep_section = document.getElementsByClass("subject_ep_section");
                Elements line_list_music = subject_ep_section.select("li");
                for (int y = 0, n = line_list_music.size(); y < n; y++) {
                    if (y == 0) {
                        continue;
                    }
                    String text = line_list_music.get(y).select("h6").text().replace(y + "", "");
                    if (text != null && !text.isEmpty()) {
                        songJSON.put(text);
                    }
                }
            }

            document = Jsoup.connect(url3).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            Element columnInSubjectA = document.getElementById("columnInSubjectA");
            Elements light_odd = columnInSubjectA.getElementsByClass("light_odd");
            for (int i = 0, total = light_odd.size(); i < total; i++) {
                JSONObject itemJson = new JSONObject();
                Element item = light_odd.get(i);
                Elements avatar = item.getElementsByClass("avatar");
//                Integer cId = Integer.valueOf(avatar.select("a").attr("href").split("character/")[1]).intValue();
                String pic = avatar.select("img").attr("src");
//                itemJson.put("cId" , cId);
                itemJson.put("image", pic);
                Elements clearit = item.getElementsByClass("clearit");
                if (clearit != null && clearit.size() > 0) {
                    Elements h2 = clearit.get(0).select("h2");
                    Integer cId = Integer.valueOf(h2.select("a").attr("href").split("character/")[1]).intValue();
                    String name = h2.select("a").text();
                    String nameCn = h2.select("span").text();
                    itemJson.put("cId", cId);
                    itemJson.put("name", name);
                    itemJson.put("nameCn", nameCn.replace("/", "").replace(" ", ""));
                    Elements crt_info = clearit.get(0).getElementsByClass("crt_info");
                    if (crt_info != null && crt_info.size() > 0) {
                        Elements badge_job = crt_info.get(0).getElementsByClass("badge_job");
                        if (badge_job != null && badge_job.size() > 0) {
                            String role_name = badge_job.get(0).text();
                            itemJson.put("role_name", role_name);
                        }
                    }
                    Elements actorBadge = clearit.get(0).getElementsByClass("actorBadge");
                    if (actorBadge != null && actorBadge.size() > 0) {
                        JSONObject actors = new JSONObject();
                        Elements avatar1 = actorBadge.get(0).getElementsByClass("avatar");
                        Integer pId = Integer.valueOf(actorBadge.select("a").attr("href").split("person/")[1]).intValue();
                        String pic1 = avatar1.select("img").attr("src");
                        String name1 = actorBadge.select("p").select("a").text();
                        String nameCn1 = actorBadge.select("p").select("small").text();
                        actors.put("pId", pId);
                        actors.put("avatar", pic1);
                        actors.put("name", name1);
                        actors.put("nameCn", nameCn1.replace("/", "").replace(" ", ""));
                        itemJson.put("actors", actors);
                    }
                }

                crtJSON.put(itemJson);
            }
//            System.out.println(result.toString());
            return result;
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
            if (headerSubject == null) {
                return jsonObject;
            }
            if (headerSubject.select("h1").select("a").text().isEmpty()) {
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
            if (headerSubject == null) {
                return jsonObject;
            }
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

    public static synchronized List<Integer> characterType2() {
        List<Integer> engineAllId = new ArrayList<>();
        String url = "http://bangumi.tv/character?type=2&page=%d";
        for (int i = 1; i <= 13; i++) {
            try {
                Document document = Jsoup.connect(String.format(url, i)).ignoreContentType(true)
                        .data("jquery", "java").userAgent("Mozilla")
                        .cookie("auth", "token").timeout(50000).get();
                Element columnCrtBrowserB = document.getElementById("columnCrtBrowserB");
                Elements browserCrtList = columnCrtBrowserB.getElementsByClass("browserCrtList");
                Elements light_odd = browserCrtList.get(0).getElementsByClass("light_odd");
                for (int j = 0, total = light_odd.size(); j < total; j++) {
                    Element item = light_odd.get(j);
                    Elements avatar = item.getElementsByClass("avatar");
                    String str = avatar.select("a").attr("href");
                    engineAllId.add(Integer.valueOf(str.split("/character/")[1]).intValue());
//                    System.out.println(Integer.valueOf(str.split("/character/")[1]).intValue());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(engineAllId.size());
        System.out.println(engineAllId.toString());
        return engineAllId;
    }

    public static synchronized List<Integer> characterType3() {
        List<Integer> engineAllId = new ArrayList<>();
        String url = "http://bangumi.tv/character?type=3&page=%d";
        for (int i = 1; i <= 13; i++) {
            try {
                Document document = Jsoup.connect(String.format(url, i)).ignoreContentType(true)
                        .data("jquery", "java").userAgent("Mozilla")
                        .cookie("auth", "token").timeout(50000).get();
                Element columnCrtBrowserB = document.getElementById("columnCrtBrowserB");
                Elements browserCrtList = columnCrtBrowserB.getElementsByClass("browserCrtList");
                Elements light_odd = browserCrtList.get(0).getElementsByClass("light_odd");
                for (int j = 0, total = light_odd.size(); j < total; j++) {
                    Element item = light_odd.get(j);
                    Elements avatar = item.getElementsByClass("avatar");
                    String str = avatar.select("a").attr("href");
                    engineAllId.add(Integer.valueOf(str.split("/character/")[1]).intValue());
//                    System.out.println(Integer.valueOf(str.split("/character/")[1]).intValue());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(engineAllId.size());
        System.out.println(engineAllId.toString());
        return engineAllId;
    }

    public static synchronized List<Integer> characterType4() {
        List<Integer> engineAllId = new ArrayList<>();
        String url = "http://bangumi.tv/character?type=4&page=%d";
        for (int i = 1; i <= 20; i++) {
            try {
                Document document = Jsoup.connect(String.format(url, i)).ignoreContentType(true)
                        .data("jquery", "java").userAgent("Mozilla")
                        .cookie("auth", "token").timeout(50000).get();
                Element columnCrtBrowserB = document.getElementById("columnCrtBrowserB");
                Elements browserCrtList = columnCrtBrowserB.getElementsByClass("browserCrtList");
                Elements light_odd = browserCrtList.get(0).getElementsByClass("light_odd");
                for (int j = 0, total = light_odd.size(); j < total; j++) {
                    Element item = light_odd.get(j);
                    Elements avatar = item.getElementsByClass("avatar");
                    String str = avatar.select("a").attr("href");
                    engineAllId.add(Integer.valueOf(str.split("/character/")[1]).intValue());
//                    System.out.println(Integer.valueOf(str.split("/character/")[1]).intValue());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(engineAllId.size());
        System.out.println(engineAllId.toString());
        return engineAllId;
    }

    public void ss(){
        String url = "http://api.bgm.tv/calendar";
    }
}
