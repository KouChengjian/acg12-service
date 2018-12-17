package com.acg12.utils.res;

import com.acg12.constant.SubjectStaffConstant;
import com.acg12.entity.dto.Acg12CharacterDto;
import com.acg12.entity.dto.Acg12PersonDto;
import com.acg12.entity.dto.Acg12SubjectDto;
import com.acg12.entity.po.*;
import com.acg12.utils.JsonParse;
import com.acg12.utils.UrlEncoderUtil;
import com.acg12.utils.checkoutjson.CheckoutJsonUtil;
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
public class BgmResourceUtil {

    /**
     * @param key
     * @param type  1 书籍 2 动画 3 音乐 4 游戏 6 三次元   书籍包括漫画和小说 分成 7、8  10、虚拟角色 11、现实角色
     * @param start
     */

    public static synchronized JSONObject getBgmSearchSubjectList(String key, int type, int start, JSONArray cutItem) {
        System.setProperty("http.proxyHost", "localhost");
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
                item.remove("summary");
                item.remove("rating");
                item.remove("rank");
                item.remove("collection");
                JSONObject jsonObject1 = item.getJSONObject("images");
                item.put("image", jsonObject1.getString("grid"));
                item.remove("images");
                item.remove("air_weekday");
                item.remove("eps");
                item.remove("eps_count");
                item.remove("url");
                item.remove("air_date");
                item.remove("eps_count");
                int ty = item.getInt("type");
                String s1 = "";
                if (ty == 1) {
                    s1 = "书籍";
                } else if (ty == 2) {
                    s1 = "动画";
                } else if (ty == 3) {
                    s1 = "音乐";
                } else if (ty == 4) {
                    s1 = "游戏";
                } else if (ty == 6) {
                    s1 = "三次元";
                }
                item.remove("type");
                item.put("type", 0);
                item.put("typeName", s1);
            }
            JSONArray jsonArray = new JSONArray();
            if (cutItem != null) {
                for (int j = 0; j < cutItem.length(); j++) {
                    jsonArray.put(cutItem.getJSONObject(j));
                }
            }
            for (int k = 0; k < list.length(); k++) {
                jsonArray.put(list.getJSONObject(k));
            }
            jsonObject.remove("list");
            jsonObject.put("list", jsonArray);
            System.err.println(jsonObject.toString());
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    // 搜索关键字获取列表
    public static synchronized JSONObject getBgmSearchSubjectList(String key, int type, int start) {
        return getBgmSearchSubjectList(key, type, start, null);
    }

    public static synchronized JSONArray getBgmSearchPresonList(String key) {
        // http://bangumi.tv/mono_search/%s?cat=crt
        // http://bangumi.tv/mono_search/%s?cat=prsn
        // http://bangumi.tv/mono_search/%s?cat=all
        String url = String.format("http://bangumi.tv/mono_search/%s?cat=crt", UrlEncoderUtil.hasUrlEncoded(key) ? key : UrlEncoderUtil.encode(key));
        try {
            JSONArray jsonArray = new JSONArray();
            Document document = Jsoup.connect(url).ignoreContentType(true)
                    .cookie("chii_sid", "IbiAB9")
                    .cookie("chii_searchDateLine", "1530838844")
                    .cookie("__utma", "1.683889927.1529480300.1529715637.1530838840.4")
                    .cookie("__utmc", "1")
                    .cookie("__utmz", "1.1530838840.4.3.utmcsr=baidu.com|utmccn=(referral)|utmcmd=referral|utmcct=/")
                    .cookie("__utmt", "1")
                    .cookie("__utmb", "1.8.10.1530838840")
                    .data("jquery", "java").userAgent("Mozilla")
                    .timeout(50000).get();
//            System.out.println(document.body());
            Element columnSearchB = document.getElementById("columnSearchB");
            Elements light_odd = columnSearchB.getElementsByClass("light_odd");
            for (int i = 0; i < light_odd.size(); i++) {
                Element item = light_odd.get(i);
                String id = item.getElementsByClass("avatar").attr("href").split("character/")[1];
                String avatar = item.getElementsByClass("avatar").select("img").attr("src");
                String[] name = item.select("h2").text().split(" / ");
                System.out.println(id + avatar + name);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", Integer.valueOf(id).intValue());
                jsonObject.put("image", "http:" + avatar);
                for (int j = 0; j < name.length; j++) {
                    if (j == 0) {
                        jsonObject.put("name", name[j]);
                    } else if (j == 1) {
                        jsonObject.put("nameCn", name[j]);
                    }
                }
                jsonObject.put("type", 1);
                jsonObject.put("typeName", "人物");
                jsonArray.put(jsonObject);
            }
            return jsonArray;
        } catch (Exception e) {
            System.out.println(e.toString());
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
            content = CheckoutJsonUtil.stringToJson(content);
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

    public static String getCalendarList() {
        try {
            String url = "http://api.bgm.tv/calendar";
            Document document = Jsoup.connect(url).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
//            System.out.println(document.body().text());

            JSONArray jsonArray = new JSONArray(document.body().text());
            for (int i = 0, num = jsonArray.length(); i < num; i++) {
                JSONObject jsonObject = JsonParse.getJSONObject(jsonArray, i);
                System.out.println(jsonObject.toString());
                JSONArray items = JsonParse.getJSONArray(jsonObject, "items");
                for (int j = 0, total = items.length(); j < total; j++) {
                    JSONObject item = JsonParse.getJSONObject(items, j);

                    JSONObject images = JsonParse.getJSONObject(item, "images");
                    String image = JsonParse.getString(images, "common");
                    item.remove("images");
                    item.remove("collection");
                    item.remove("url");
                    item.remove("rating");
                    item.remove("count");
                    item.put("image", image);
                    System.out.println(item.toString());
                }
            }

            return jsonArray.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized Acg12SubjectDto getSubjectDto(int sId) {
//        System.setProperty("http.proxyHost", "localhost");
//        System.setProperty("http.proxyPort", "8888");
//        System.setProperty("https.proxyHost", "localhost");
//        System.setProperty("https.proxyPort", "8888");
        String url1 = String.format("http://api.bgm.tv/subject/%d?responseGroup=large", sId);
        String url2 = String.format("http://bangumi.tv/subject/%d", sId);
        String url3 = String.format("http://bangumi.tv/subject/%d/characters", sId);
        try {
            Document document = Jsoup.connect(url1).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.body().text();
//            System.out.printf(content);
            if (content == null || content.isEmpty()) {
                return null;
            }
//            System.out.println(content);
//            System.out.println(StringUtil.stringToJson(content));
//            content = CheckoutJsonUtil.stringToJson(content);
//            System.out.printf(content);
            JSONObject contentJson = new JSONObject(content);
            if (!contentJson.isNull("code")) {
                return null;
            }
            Acg12SubjectEntity acg12SubjectEntity = new Acg12SubjectEntity();
            List<Acg12SubjectDetailEntity> subjectDetailEntityList = new ArrayList<>();
            List<Acg12SubjectStaffEntity> subjectStaffEntityList = new ArrayList<>();
            List<Acg12SubjectCrtEntity> subjectCrtEntityArrayList = new ArrayList<>();
            List<Acg12SubjectSongEntity> subjectSongEntityList = new ArrayList<>();
            List<Acg12SubjectOffprintEntity> subjectOffprintEntityList = new ArrayList<>();

            int type = JsonParse.getInt(contentJson, "type");
            acg12SubjectEntity.setSId(JsonParse.getInt(contentJson, "id"));
            acg12SubjectEntity.setType(type);
            acg12SubjectEntity.setName(JsonParse.getString(contentJson, "name"));
            acg12SubjectEntity.setNameCn(JsonParse.getString(contentJson, "name_cn"));
            acg12SubjectEntity.setSummary(JsonParse.getString(contentJson, "summary"));
            acg12SubjectEntity.setImage(contentJson.getJSONObject("images").getString("large"));
            acg12SubjectEntity.setEpsCount(JsonParse.getInt(contentJson, "eps_count"));
            acg12SubjectEntity.setAirDate(JsonParse.getString(contentJson, "air_date"));
            acg12SubjectEntity.setAirWeekday(JsonParse.getInt(contentJson, "air_weekday"));

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
                    acg12SubjectEntity.setTypeName(typeName);
                }
            } else if (type == 2) {
                hashMap = SubjectStaffConstant.animationMap;
                String typeName = headerSubject.select("h1").select("small").text();
                if (!typeName.isEmpty()) {
                    acg12SubjectEntity.setTypeName(typeName);
                }
            } else if (type == 3) {
                hashMap = SubjectStaffConstant.musicMap;
                acg12SubjectEntity.setTypeName("音乐");
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
                    if (acg12SubjectEntity.getAirDate().equals("0000-00-00")) {
                        if (key.contains("放送开始") || key.contains("发售日") || key.contains("开始")) {
                            item.select("span").remove();
                            acg12SubjectEntity.setAirDate(item.text());
                        }
                    }
                    continue;
                }

                if (hashMap.containsKey(key)) {
                    Elements a = item.select("a");
                    if (a.isEmpty()) {
                        item.select("span").remove();
//                        System.out.println(item.text());
                        Acg12SubjectStaffEntity staffEntity = new Acg12SubjectStaffEntity();
                        staffEntity.setSId(sId);
                        staffEntity.setName(item.text());
                        staffEntity.setJob(key);
                        subjectStaffEntityList.add(staffEntity);
                    } else {
                        for (int j = 0, num = a.size(); j < num; j++) {
                            Element element = a.get(j);
                            String pId = element.attr("href").split("person/")[1];
                            Acg12SubjectStaffEntity staffEntity = new Acg12SubjectStaffEntity();
                            staffEntity.setSId(sId);
                            staffEntity.setPId(Integer.valueOf(pId));
                            staffEntity.setName(item.text());
                            staffEntity.setJob(key);
                            subjectStaffEntityList.add(staffEntity);
                        }
                    }
                } else {
                    item.select("span").remove();
                    if (type == 1) {
                    } else if (type == 2) {
                    } else if (type == 3) {
                    } else if (type == 4) {
                        if (key.contains("游戏类型")) {
                            acg12SubjectEntity.setTypeName(item.text());
                            continue;
                        }
                    } else if (type == 6) {
                        if (key.contains("类型")) {
                            acg12SubjectEntity.setTypeName(item.text().replace(" / ", ","));
                            continue;
                        }
                    }
                    if (key.contains("结束")) {
                        acg12SubjectEntity.setEndDate(item.text());
                        continue;
                    } else {
                        JSONObject itemJson = new JSONObject();
                        itemJson.put("otherTitle", key);
                        itemJson.put("otherValue", item.text());
                        Acg12SubjectDetailEntity detailEntity = new Acg12SubjectDetailEntity();
                        detailEntity.setSId(sId);
                        detailEntity.setOtherTitle(key);
                        detailEntity.setOtherValue(item.text());
                        subjectDetailEntityList.add(detailEntity);
                    }
                }
            }

            if (type == 1) {
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

                    Acg12SubjectOffprintEntity offprintEntity = new Acg12SubjectOffprintEntity();
                    offprintEntity.setSId(Integer.valueOf(id));
                    offprintEntity.setName(name);
                    offprintEntity.setImage(pic);
                    subjectOffprintEntityList.add(offprintEntity);
                }
            } else if (type == 3) {
                Elements subject_ep_section = document.getElementsByClass("subject_ep_section");
                Elements line_list_music = subject_ep_section.select("li");
                for (int y = 0, n = line_list_music.size(); y < n; y++) {
                    if (y == 0) {
                        continue;
                    }
                    String text = line_list_music.get(y).select("h6").text().replace(y + "", "");
                    if (text != null && !text.isEmpty()) {
                        Acg12SubjectSongEntity songEntity = new Acg12SubjectSongEntity();
                        songEntity.setSId(sId);
                        songEntity.setTitle(text);
                        subjectSongEntityList.add(songEntity);
                    }
                }
            }
            // car
            document = Jsoup.connect(url3).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            Element columnInSubjectA = document.getElementById("columnInSubjectA");
            Elements light_odd = columnInSubjectA.getElementsByClass("light_odd");
            for (int i = 0, total = light_odd.size(); i < total; i++) {
                Acg12SubjectCrtEntity crtEntity = new Acg12SubjectCrtEntity();
//                JSONObject itemJson = new JSONObject();
                Element item = light_odd.get(i);
                Elements avatar = item.getElementsByClass("avatar");
//                Integer cId = Integer.valueOf(avatar.select("a").attr("href").split("character/")[1]).intValue();
                String pic = avatar.select("img").attr("src");
//                itemJson.put("cId" , cId);
                crtEntity.setImage(pic);
                Elements clearit = item.getElementsByClass("clearit");
                if (clearit != null && clearit.size() > 0) {
                    Elements h2 = clearit.get(0).select("h2");
                    Integer cId = Integer.valueOf(h2.select("a").attr("href").split("character/")[1]).intValue();
                    String name = h2.select("a").text();
                    String nameCn = h2.select("span").text();
                    crtEntity.setCId(cId);
                    crtEntity.setName(name);
                    crtEntity.setNameN(nameCn.replace("/", "").replace(" ", ""));
                    Elements crt_info = clearit.get(0).getElementsByClass("crt_info");
                    if (crt_info != null && crt_info.size() > 0) {
                        Elements badge_job = crt_info.get(0).getElementsByClass("badge_job");
                        if (badge_job != null && badge_job.size() > 0) {
                            String role_name = badge_job.get(0).text();
                            crtEntity.setRoleName(role_name);
                        }
                    }
                    Elements actorBadge = clearit.get(0).getElementsByClass("actorBadge");
                    if (actorBadge != null && actorBadge.size() > 0) {
                        Elements avatar1 = actorBadge.get(0).getElementsByClass("avatar");
                        Integer pId = Integer.valueOf(actorBadge.select("a").attr("href").split("person/")[1]).intValue();
                        String pic1 = avatar1.select("img").attr("src");
                        String name1 = actorBadge.select("p").select("a").text();
                        String nameCn1 = actorBadge.select("p").select("small").text();
                        crtEntity.setPId(pId);
                        crtEntity.setPImage(pic1);
                        crtEntity.setPName(name1);
                        crtEntity.setPNameCn(nameCn1.replace("/", "").replace(" ", ""));
                    }
                }
                subjectCrtEntityArrayList.add(crtEntity);
            }

            Acg12SubjectDto subjectDto = new Acg12SubjectDto();
            subjectDto.copy(acg12SubjectEntity);
            if (subjectDetailEntityList.size() != 0) {
                subjectDto.setDetailList(subjectDetailEntityList);
            }
            if (subjectStaffEntityList.size() != 0) {
                subjectDto.setStaffList(subjectStaffEntityList);
            }
            if (subjectCrtEntityArrayList.size() != 0) {
                subjectDto.setCrtList(subjectCrtEntityArrayList);
            }
            if (subjectSongEntityList.size() != 0) {
                subjectDto.setSongList(subjectSongEntityList);
            }
            if (subjectOffprintEntityList.size() != 0) {
                subjectDto.setOffprintList(subjectOffprintEntityList);
            }

            return subjectDto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized Acg12PersonDto getPersonDto(int pId) {
        try {
            String url3 = String.format("http://bangumi.tv/person/%d", pId);
            Document document = Jsoup.connect(url3).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();

            Acg12PersonEntity personEntity = new Acg12PersonEntity();
            List<Acg12PersonDetailEntity> personDetailEntityList = new ArrayList<>();

            Element headerSubject = document.getElementById("headerSubject");
            if (headerSubject == null) {
                return null;
            }
            if (headerSubject.select("h1").select("a").text().isEmpty()) {
                return null;
            }

            Element columnCrtB = document.getElementById("columnCrtB");
            Elements clearit = columnCrtB.getElementsByClass("clearit").select("h2");
            Elements detail = columnCrtB.getElementsByClass("detail");
            Elements center = document.getElementsByClass("infobox");
            String image = center.select("img").attr("src");

            personEntity.setPId(pId);
            personEntity.setImage(image);
            personEntity.setName(headerSubject.select("h1").select("a").text());
            personEntity.setSummary(detail.text());

            String[] jobs = clearit.text().split(":");
            if (jobs.length == 2) {
                jobs = jobs[1].split(" ");
                String jobList = "";
                for (int i = 0, num = jobs.length; i < num; i++) {
                    if (jobs[i] == null || jobs[i].isEmpty()) {
                        continue;
                    }
                    int type;
                    if (jobs[i].contains("声优")) {
                        type = 1;
                    } else if (jobs[i].contains("漫画家")) {
                        type = 2;
                    } else if (jobs[i].contains("制作人")) {
                        type = 3;
                    } else if (jobs[i].contains("音乐人")) {
                        type = 4;
                    } else if (jobs[i].contains("演员")) {
                        type = 6;
                    } else if (jobs[i].contains("绘师")) {
                        type = 7;
                    } else if (jobs[i].contains("作家")) {
                        type = 8;
                    } else {
                        type = 0;
                    }
                    if (type != 0) {
                        if (jobList.isEmpty()) {
                            jobList += "" + type;
                        } else {
                            jobList += "、" + type;
                        }
                    }
                }
                personEntity.setType(jobList);
            }

            Element infobox = document.getElementById("infobox");
            Elements lis = infobox.select("li");
            String alias = "";
            for (int i = 0, total = lis.size(); i < total; i++) {
                Element item = lis.get(i);
                Elements span = item.select("span");

                String key = span.text().replace(":", "");
//                System.err.println(key);
                item.select("span").remove();

                if (key.equals("简体中文名")) {
                    personEntity.setNameCn(item.text());
                } else if (key.equals("性别")) {
                    String gender = item.text();
                    personEntity.setGender(gender.equals("男") ? "1" : "2");
                } else if (key.equals("生日")) {
                    personEntity.setBirthday(item.text());
                } else if (key.equals("血型")) {
                    String bloodtype = item.text();
                    if (bloodtype.isEmpty()) {
                        personEntity.setBloodtype(0);
                    } else if (bloodtype.contains("A")) {
                        personEntity.setBloodtype(1);
                    } else if (bloodtype.contains("B")) {
                        personEntity.setBloodtype(2);
                    } else if (bloodtype.contains("AB")) {
                        personEntity.setBloodtype(3);
                    } else if (bloodtype.contains("O")) {
                        personEntity.setBloodtype(4);
                    }
                } else if (key.equals("体重")) {
                    personEntity.setWeight(item.text());
                } else if (key.equals("身高")) {
                    personEntity.setHeight(item.text());
                } else if (key.equals("别名")) {
                    if (alias.isEmpty()) {
                        alias += "" + item.text();
                    } else {
                        if (item.text().length() < 400) {
                            alias += "、" + item.text();
                        }
                    }
                } else {
                    Acg12PersonDetailEntity personDetailEntity = new Acg12PersonDetailEntity();
                    personDetailEntity.setOtherTitle(key);
                    personDetailEntity.setOtherValue(item.text());
                    personDetailEntityList.add(personDetailEntity);
                }
            }
            personEntity.setAlias(alias);
//            System.err.println(jsonObject.toString());
            Acg12PersonDto acg12PersonDto = new Acg12PersonDto();
            acg12PersonDto.copy(personEntity);
            acg12PersonDto.setDetailList(personDetailEntityList);
            return acg12PersonDto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized Acg12CharacterDto getCharacterDto(int cId) {
        try {
            String url3 = String.format("http://bangumi.tv/character/%d", cId);
            Document document = Jsoup.connect(url3).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();

//            JSONObject jsonObject = new JSONObject();
//            JSONArray aliasJson = new JSONArray();
//            JSONArray otherJson = new JSONArray();

            Element headerSubject = document.getElementById("headerSubject");
            if (headerSubject == null) {
                return null;
            }
            Element columnCrtB = document.getElementById("columnCrtB");
            Elements detail = columnCrtB.getElementsByClass("detail");
            Elements center = document.getElementsByClass("infobox");
            String image = center.select("img").attr("src");

            Acg12CharacterEntity characterEntity = new Acg12CharacterEntity();
            List<Acg12CharacterDetailEntity> detailList = new ArrayList<>();
            characterEntity.setCId(cId);
            characterEntity.setImage(image);
            characterEntity.setName(headerSubject.select("h1").select("a").text());
            characterEntity.setSummary(detail.text());

            Element infobox = document.getElementById("infobox");
            Elements lis = infobox.select("li");
            String alias = "";
            for (int i = 0, total = lis.size(); i < total; i++) {
                Element item = lis.get(i);
                Elements span = item.select("span");

                String key = span.text().replace(":", "");
//                System.err.println(key);
                item.select("span").remove();
                if (key.equals("简体中文名")) {
                    characterEntity.setNameCn(item.text());
                } else if (key.equals("性别")) {
                    String gender = item.text();
                    characterEntity.setGender(gender.equals("男") ? 1 : 2);
                } else if (key.equals("生日")) {
                    characterEntity.setBirthday(item.text());
                } else if (key.equals("血型")) {
                    String bloodtype = item.text();
                    if (bloodtype.isEmpty()) {
                        characterEntity.setBloodtype(0);
                    } else if (bloodtype.contains("A")) {
                        characterEntity.setBloodtype(1);
                    } else if (bloodtype.contains("B")) {
                        characterEntity.setBloodtype(2);
                    } else if (bloodtype.contains("AB")) {
                        characterEntity.setBloodtype(3);
                    } else if (bloodtype.contains("O")) {
                        characterEntity.setBloodtype(4);
                    }
                } else if (key.equals("体重")) {
                    characterEntity.setWeight(item.text());
                } else if (key.equals("身高")) {
                    characterEntity.setHeight(item.text());
                } else if (key.equals("别名")) {
                    if (alias.isEmpty()) {
                        alias += "" + item.text();
                    } else {
                        if (item.text().length() < 400) {
                            alias += "、" + item.text();
                        }
                    }
                } else {
                    Acg12CharacterDetailEntity detailEntity = new Acg12CharacterDetailEntity();
                    detailEntity.setOtherTitle(key);
                    detailEntity.setOtherValue(item.text());
                    detailList.add(detailEntity);
                }
            }
            characterEntity.setAlias(alias);

            Acg12CharacterDto characterDto = new Acg12CharacterDto();
            characterDto.copy(characterEntity);
            characterDto.setDetailList(detailList);
            return characterDto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 机体 2   251
    public static synchronized List<Integer> characterType2() {
        List<Integer> list = new ArrayList<>();
        String url = "http://bangumi.tv/character?type=2&page=%d";
        try {
            Document document = Jsoup.connect(String.format(url, 1)).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            Element multipage = document.getElementById("multipage");
            Elements page_inner = multipage.getElementsByClass("page_inner");
            Elements p_edge = page_inner.get(0).getElementsByClass("p_edge");
            String s = p_edge.text().replace("&nbsp;", "").replace(" ", "").replace("(1/", "").replace(")", "");
            if (s == null || s.isEmpty()) {
                return list;
            }
            int num = Integer.valueOf(s);
            System.out.println(num+"=======");
            for (int i = 1; i <= num; i++) {
                document = Jsoup.connect(String.format(url, i)).ignoreContentType(true)
                        .data("jquery", "java").userAgent("Mozilla")
                        .cookie("auth", "token").timeout(50000).get();
                Element columnCrtBrowserB = document.getElementById("columnCrtBrowserB");
                Elements browserCrtList = columnCrtBrowserB.getElementsByClass("browserCrtList");
                Elements light_odd = browserCrtList.get(0).getElementsByClass("light_odd");
                for (int j = 0, total = light_odd.size(); j < total; j++) {
                    Element item = light_odd.get(j);
                    Elements avatar = item.getElementsByClass("avatar");
                    String str = avatar.select("a").attr("href");
                    list.add(Integer.valueOf(str.split("/character/")[1]).intValue());
//                    System.out.println(Integer.valueOf(str.split("/character/")[1]).intValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 舰船 3   122
    public static synchronized List<Integer> characterType3() {
        List<Integer> list = new ArrayList<>();
        String url = "http://bangumi.tv/character?type=3&page=%d";
        try {
            Document document = Jsoup.connect(String.format(url, 1)).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            Element multipage = document.getElementById("multipage");
            Elements page_inner = multipage.getElementsByClass("page_inner");
//            System.out.println(page_inner.toString());
            Elements p_edge = page_inner.get(0).getElementsByClass("p_edge");
            String s ;
            if(p_edge.size() == 0){
                Elements als = page_inner.select("a");
                Element a =als.get(als.size() -2);
//                System.out.println(a.text()+"");
                s = a.text();
            } else {
                 s = p_edge.text().replace("&nbsp;", "").replace(" ", "").replace("(1/", "").replace(")", "");
            }
            if (s == null || s.isEmpty()) {
                return list;
            }
            int num = Integer.valueOf(s);
            System.out.println(num+"=======");
            for (int i = 1; i <= num; i++) {
                document = Jsoup.connect(String.format(url, i)).ignoreContentType(true)
                        .data("jquery", "java").userAgent("Mozilla")
                        .cookie("auth", "token").timeout(50000).get();
                Element columnCrtBrowserB = document.getElementById("columnCrtBrowserB");
                Elements browserCrtList = columnCrtBrowserB.getElementsByClass("browserCrtList");
                Elements light_odd = browserCrtList.get(0).getElementsByClass("light_odd");
                for (int j = 0, total = light_odd.size(); j < total; j++) {
                    Element item = light_odd.get(j);
                    Elements avatar = item.getElementsByClass("avatar");
                    String str = avatar.select("a").attr("href");
                    list.add(Integer.valueOf(str.split("/character/")[1]).intValue());
//                    System.out.println(Integer.valueOf(str.split("/character/")[1]).intValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 组织 4   394
    public static synchronized List<Integer> characterType4() {
        List<Integer> list = new ArrayList<>();
        String url = "http://bangumi.tv/character?type=4&page=%d";
        try {
            Document document = Jsoup.connect(String.format(url, 1)).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            Element multipage = document.getElementById("multipage");
            Elements page_inner = multipage.getElementsByClass("page_inner");
            Elements p_edge = page_inner.get(0).getElementsByClass("p_edge");
            String s = p_edge.text().replace("&nbsp;", "").replace(" ", "").replace("(1/", "").replace(")", "");
            if (s == null || s.isEmpty()) {
                return list;
            }
            int num = Integer.valueOf(s);
            System.out.println(num+"=======");
            for (int i = 1; i <= num; i++) {
                document = Jsoup.connect(String.format(url, i)).ignoreContentType(true)
                        .data("jquery", "java").userAgent("Mozilla")
                        .cookie("auth", "token").timeout(50000).get();
                Element columnCrtBrowserB = document.getElementById("columnCrtBrowserB");
                Elements browserCrtList = columnCrtBrowserB.getElementsByClass("browserCrtList");
                Elements light_odd = browserCrtList.get(0).getElementsByClass("light_odd");
                for (int j = 0, total = light_odd.size(); j < total; j++) {
                    Element item = light_odd.get(j);
                    Elements avatar = item.getElementsByClass("avatar");
                    String str = avatar.select("a").attr("href");
                    list.add(Integer.valueOf(str.split("/character/")[1]).intValue());
//                    System.out.println(Integer.valueOf(str.split("/character/")[1]).intValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
