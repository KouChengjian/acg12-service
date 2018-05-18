package com.acg12.utils.crawler.http;

import com.acg12.conf.UrlConstant;
import com.acg12.entity.po.Album;
import com.acg12.entity.po.Palette;
import com.acg12.entity.dto.Video;
import com.acg12.conf.Constant;
import com.acg12.utils.StringUtil;
import com.acg12.utils.TimeUtil;
import com.acg12.utils.UrlEncoderUtil;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kouchengjian on 2017/3/7.
 */
public class ResRequest {

    /**
     * --------------------------------------动漫之家资源--------------------------------------------
     */

    // 获取每日快报
    public static synchronized JSONArray getNewList(String pager) {
        try {
            Document document = Jsoup.connect(String.format(UrlConstant.URL_DONGMANZHIJIA_NEWS, pager)).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.body().text();
            if (content == null || content.isEmpty()) {
                return null;
            }
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                jsonObject.remove("col_pic_url");
                jsonObject.remove("comment_amount");
                jsonObject.remove("author_uid");
                jsonObject.remove("is_foreign");
                jsonObject.remove("foreign_url");
                jsonObject.remove("nickname");
                jsonObject.remove("author_id");
                jsonObject.remove("foreign_url");
                jsonObject.remove("cover");
                jsonObject.remove("from_name");
                jsonObject.remove("from_url");
                String row_pic_url = jsonObject.getString("row_pic_url");
//                row_pic_url = row_pic_url.replace("http://","https://");
                jsonObject.remove("row_pic_url");
                jsonObject.put("pic_url", row_pic_url);
            }
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //

    /**
     * --------------------------------------萌娘百科资源--------------------------------------------
     */
    // 萌娘百科 搜索
    public static synchronized JSONArray getSearchKeyList(String title) {
        JSONArray jsonArray = new JSONArray();
        try {
            Document document = Jsoup.connect(UrlConstant.URL_MENGNIANBAIKE_SEARCH + title).ignoreContentType(true)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            String content = document.body().text();
            if (content == null || content.isEmpty()) {
                return jsonArray;
            }
            System.err.println(content.toString());

            JSONObject jsonObject = new JSONObject(content);
            JSONObject query = jsonObject.getJSONObject("query");
            JSONObject pages = query.getJSONObject("pages");
            Iterator iterator = pages.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                JSONObject value = pages.getJSONObject(key);
                if (!value.isNull("thumbnail")) {
                    JSONObject thumbnai = value.getJSONObject("thumbnail");
                    String source = thumbnai.getString("source");
                    value.remove("thumbnail");
                    value.put("source", source);
                }
                jsonArray.put(value);
                System.err.println(value.toString());
            }
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    // 萌娘百科 搜索详情
    public static synchronized void getSearchKeyInfo(String title) {
        try {
            Document document = Jsoup.connect(UrlConstant.URL_MENGNIANBAIKE_SEARCH_INFO + title)
                    .data("jquery", "java").userAgent("Mozilla")
                    .cookie("auth", "token").timeout(50000).get();
            Element mw_mf_viewport = document.body().getElementById("mw-mf-viewport");
            Element mw_mf_page_center = mw_mf_viewport.getElementById("mw-mf-page-center");
            Element content = mw_mf_page_center.getElementById("content");
            Element bodyContent = content.getElementById("bodyContent");
            Element mw_content_text = bodyContent.getElementById("mw-content-text");
            Element mw_parser_output = mw_content_text.getElementsByClass("mw-parser-output").first();
            Element mf_section_0 = mw_parser_output.getElementById("mf-section-0");
//            System.err.println(mf_section_0);
            DataParse.parseInfoHeader(mf_section_0);
            DataParse.parseInfoBody(mw_parser_output);

            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    /**
     * ---------------------------------------bilibili资源-------------------------------------------
     */

    /**
     * 横幅
     */
    public static synchronized List<Video> getBanner() {
        List<Video> bannerList = new ArrayList<Video>();
        try {
            URL url = new URL(Constant.URL_HOME_BRAND);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            InputStream inStream = conn.getInputStream();
            String content = "";
            if ("gzip".equals(conn.getContentEncoding())) {
                content = StringUtil.readDataForZgip(inStream, "utf-8");
            } else {
                content = StringUtil.readDataForZgip(inStream);
            }
            conn.disconnect();
            if (content != null && !content.isEmpty()) {
                JSONObject bannerjson = new JSONObject(content);
                JSONArray array = bannerjson.getJSONArray("result");
                for (int i = 0, num = array.length(); i < num; i++) {
                    Video item = new Video();
                    item.setPic(array.getJSONObject(i).getString("img").toString());
                    item.setTitle(array.getJSONObject(i).getString("title").toString());
                    item.setUrlInfo(array.getJSONObject(i).getString("link").toString());
                    bannerList.add(item);
                }
//                System.out.println(new Gson().toJson(bannerList));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bannerList;
    }

    /**
     * 视频内容
     */
    public static synchronized List<List<Video>> getHomeLists() {
        List<List<Video>> videoLl = new ArrayList<List<Video>>();
        try {
            URL url = new URL(Constant.URL_HOME_CONTENT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10 * 1000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200)
                throw new RuntimeException("请求url失败");
            InputStream is = conn.getInputStream();//拿到输入流
            String content = "";
            if ("gzip".equals(conn.getContentEncoding())) {
                content = StringUtil.readDataForZgip(is, "utf-8");
            } else {
                content = StringUtil.readDataForZgip(is);
            }
            conn.disconnect();
            if (content != null && !content.isEmpty()) {
                ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
                JSONObject bangumijson = new JSONObject(content);
                JSONObject bangumiarray = bangumijson.getJSONObject("bangumi");// 新番
                JSONObject dougaarray = bangumijson.getJSONObject("douga");//动画
//				JSONObject musicarray   = bangumijson.getJSONObject("music"); //音乐
//				JSONObject kichikuarray = bangumijson.getJSONObject("kichiku"); //鬼畜
//				JSONObject entarray     = bangumijson.getJSONObject("ent"); //娱乐
                jsonList.add(bangumiarray);
                jsonList.add(dougaarray);
//				jsonList.add(musicarray);
//				jsonList.add(kichikuarray);
//				jsonList.add(entarray);
                for (int j = 0; j < jsonList.size(); j++) {
                    ArrayList<Video> videoList = new ArrayList<Video>();
                    for (int i = 0; i < 10; i++) {
                        Video item = new Video();
                        item.setAid(jsonList.get(j).getJSONObject(i + "").getString("aid").toString());
//                        item.     setTypeid(jsonList.get(j).getJSONObject(i+"").getString("typeid").toString());
                        item.setTitle(jsonList.get(j).getJSONObject(i + "").getString("title").toString());
                        item.setSbutitle(jsonList.get(j).getJSONObject(i + "").optString("sbutitle").toString());
                        item.setPlay(jsonList.get(j).getJSONObject(i + "").getString("play").toString());
                        item.setReview(jsonList.get(j).getJSONObject(i + "").getString("review").toString());
                        item.setVideoReview(jsonList.get(j).getJSONObject(i + "").getString("video_review").toString());
                        item.setFavorites(jsonList.get(j).getJSONObject(i + "").getString("favorites").toString());
                        item.setMid(jsonList.get(j).getJSONObject(i + "").getString("mid").toString());
                        item.setAuthor(jsonList.get(j).getJSONObject(i + "").getString("author").toString());
                        item.setDescription(jsonList.get(j).getJSONObject(i + "").getString("description").toString());
                        item.setCreate(jsonList.get(j).getJSONObject(i + "").getString("create").toString());
                        item.setPic(jsonList.get(j).getJSONObject(i + "").getString("pic").toString());
                        item.setCredit(jsonList.get(j).getJSONObject(i + "").getString("credit").toString());
                        item.setCoins(jsonList.get(j).getJSONObject(i + "").getString("coins").toString());
                        item.setDuration(jsonList.get(j).getJSONObject(i + "").getString("duration").toString());
                        videoList.add(item);
                    }
                    videoLl.add(videoList);
                }
                //System.out.println(new Gson().toJson(videoLl));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoLl;
    }

    /**
     * 主页 - 更多视频
     */
    public static synchronized List<Video> getVideoTypeList(String url, String page) {
        List<Video> videoList = new ArrayList<Video>();
        try {
            System.out.println(url + page + "-" + TimeUtil.getTimeOld(System.currentTimeMillis()) + ".html");
            Document document = Jsoup.connect(url + page + "-" + TimeUtil.getTimeOld(System.currentTimeMillis()) + ".html").data("jquery", "java")
                    .userAgent("Mozilla").cookie("auth", "token")
                    .timeout(50000).get();
            String content = document.toString();
            if (content != null && !content.isEmpty()) {
                Elements divs = document.select("div.l-item");
                for (Element div : divs) {
                    Elements l_l = div.select("div.l-l");
                    Elements aa = l_l.select("a");
                    String aid = aa.attr("href").split("av")[1].replace("/", "");
                    System.out.println(aid);
                    String title = aa.attr("title");
                    System.out.println(title);
                    String imageurl = div.select("[data-img]").attr("abs:data-img");
                    System.out.println(imageurl);

                    //Element link = div.select("a[href]").get(0);
                    //Elements media = div.select("[data-img]");
                    Elements info = div.select("div.v-desc");
                    Elements v_info = div.select("div.v-info");
                    //Elements gk = v_info.select("span");
                    Elements up_info = div.select("div.up-info");
                    Elements v_date = up_info.select("span");
                    Element user = up_info.select("a[href]").get(0);

                    String gk = "0";
                    Elements gks = v_info.select("v-info-i,.gk");
                    Elements gknum = gks.select("span");
                    if (gknum.size() == 2) {
                        gk = gknum.get(1).attr("number");
                    }
                    String dm = "0";
                    Elements dms = v_info.select("v-info-i,.dm");
                    Elements dmnum = dms.select("span");
                    if (dmnum.size() == 2) {
                        dm = dmnum.get(1).attr("number");
                    }
                    String sc = "0";
                    Elements scs = v_info.select("v-info-i,.sc");
                    Elements scnum = scs.select("span");
                    if (scnum.size() == 2) {
                        sc = scnum.get(1).attr("number");
                    }
                    Video item = new Video();
                    item.setAid(aid);
                    item.setTitle(title);
                    item.setPic(imageurl);
                    item.setDescription(info.text());
                    item.setPlay(gk); // 播放
                    item.setVideoReview(dm); // 弹幕
                    item.setFavorites(sc); // 收藏
                    item.setAuthor(user.text());
                    item.setCreate(v_date.text());
                    videoList.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoList;
    }

    /**
     * 主页 - 视频信息
     */
    public static synchronized Video getVideoTypeInfo(String av) {
        String content = "";
        Video video = new Video();
        List<Video> videoList = new ArrayList<Video>();
        try {
            //System.out.println(String.format(Constant.URL_HOME_VIDEO_INFO , av));
            Document document = Jsoup.connect(String.format(Constant.URL_HOME_VIDEO_INFO, av)).data("jquery", "java")
                    .userAgent("Mozilla").cookie("auth", "token")
                    .timeout(50000).get();

            Elements listElements = document.getElementsByClass("li-wrap-content");
            //System.out.println(listElements.size());
            for (int i = 0; i < listElements.size(); i++) {
                Video item = new Video();
                item.setTitle(listElements.get(i).text());
                videoList.add(item);
            }
            Elements labelElements = document.select("[name=keywords]");
            String label = labelElements.attr("content");
            video.setSbutitle(label);
            video.setBangumiVideoList(videoList);
            //Gson gson = new Gson();
            //content = gson.toJson(video);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return video;
    }

    /**
     * 发现 - 番剧
     */
    public static synchronized List<Video> getDangumiList(String page) {
        final List<Video> videoList = new ArrayList<Video>();
        try {
            URL url = new URL(Constant.URL_FIND_BANKUN + page);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10 * 1000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200)
                throw new RuntimeException("请求url失败");
            InputStream is = conn.getInputStream();//拿到输入流
            String content = "";
            if ("gzip".equals(conn.getContentEncoding())) {
                content = StringUtil.readDataForZgip(is, "utf-8");
            } else {
                content = StringUtil.readDataForZgip(is);
            }
            conn.disconnect();
            if (content != null && !content.isEmpty()) {
                JSONObject json = new JSONObject(content);
                JSONObject result = json.getJSONObject("result");
                JSONArray list = result.getJSONArray("list");
                for (int i = 0; i < list.length(); i++) {
                    JSONObject jsonObject = list.getJSONObject(i);
                    Video item = new Video();
                    //item.setUrlInfo(jsonObject.getString("url"));
                    item.setBmId(jsonObject.getString("url").split("/anime/")[1]);
                    item.setPic(jsonObject.getString("cover"));
                    item.setTitle(jsonObject.getString("title"));
                    item.setUpdateContent("更新至" + jsonObject.getString("newest_ep_index") + "话");
                    videoList.add(item);
                }
//                Gson gson = new Gson();
//                content = gson.toJson(videoList);
                //System.out.println(content);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return videoList;
    }

    /**
     * 发现 - 番剧详情
     */
    public static synchronized Video getDangumiInfo(String bmId) {
        Video video = new Video();
        try {
            System.out.println(Constant.URL_FIND_BANKUN_INFO + bmId);
            Document document = Jsoup.connect(Constant.URL_FIND_BANKUN_INFO + bmId).data("jquery", "java")
                    .userAgent("Mozilla").cookie("auth", "token").timeout(50000).get();
            //System.out.println(document.toString());

            Elements main_inner = document.select("div.info-content");
            //System.out.println(main_inner.toString());
            Elements bangumi_preview = main_inner.select("div.bangumi-preview");
            //System.out.println(bangumi_preview.toString());
            String previewUrl = bangumi_preview.select("img").attr("abs:src");
            video.setPic(previewUrl);
            //System.out.println("previewUrl = "+previewUrl);
            Elements bangumi_info_r = main_inner.select("div.bangumi-info-r");
            //System.out.println(bangumi_info_r.toString());
            Elements b_head = bangumi_info_r.select("div.b-head");
            Elements title_h = b_head.select("h1");
            //System.out.println(title_h.toString());
            String title = title_h.attr("title");
            video.setTitle(title);
            //System.out.println("title = "+title);
            Elements taga = b_head.select("a");
            String label = "";
            for (Element tag : taga) {
                //System.out.println("tag="+tag.text());
                label += tag.text() + " ";
            }
            video.setSbutitle(label);

            Elements info_cv = bangumi_info_r.select("div.info-row").select(".info-cv");
            Elements info_row_label = info_cv.select("div.info-row-label");
//            System.out.println(info_row_label.toString());
            Elements voiceActor = info_row_label.select("span");
//            System.out.println(voiceActor.size());

            //System.out.println("label = "+label);
            Elements info_descs = bangumi_info_r.select("div.info-row,.info-desc-wrp");
            Elements info = info_descs.select("div.info-desc");
            String des = info.text();
            video.setDescription(des);
            //System.out.println("des = "+des);

            // 获取视频列表
            List<Video> bangumiVideoList = new ArrayList<Video>();
            Elements complete_list = document.getElementsByClass("complete-list");
            //System.out.println(complete_list.size());
            Elements slider_part_wrapper = complete_list.select("div.slider-part-wrapper");
            //System.out.println(slider_part_wrapper.size());
            Elements v1_bangumi_list_part_child = slider_part_wrapper.select("li.v1-bangumi-list-part-child");
            //System.out.println("episode_list = "+v1_bangumi_list_part_child.size());
            for (Element element : v1_bangumi_list_part_child) {
                Video vi = new Video();
                Elements v1_complete_text = element.select("a[href]");
                //System.out.println(v1_complete_text.toString());
                String vi_title = v1_complete_text.attr("title");
                String vi_videoUrl = v1_complete_text.attr("href");
                //System.out.println("vi_videoUrl="+vi_videoUrl);
                vi.setTitle(vi_title);
                vi.setUrlInfo(vi_videoUrl);
                bangumiVideoList.add(vi);
            }
            video.setBangumiVideoList(bangumiVideoList);

            // 季度
            List<Video> quarterViewList = new ArrayList<Video>();
            Elements v1_bangumi_list_season_wrapper = document.select("div.v1-bangumi-list-season-wrapper");
            Elements slider_list_content = v1_bangumi_list_season_wrapper.select("div.v1-bangumi-list-season-content,.slider-list-content");
            Elements v1_bangumi_list_season = slider_list_content.select("ul.v1-bangumi-list-season,.clearfix,.slider-list");
            Elements li_items = v1_bangumi_list_season.select("li");
            //System.out.println(li_items.size());
            for (Element item : li_items) {
                Video quarterVideo = new Video();
                String quarter_title = item.text();
                String data_season_id = item.attr("data-season-id");
                //System.out.println(quarter_title);
                //System.out.println(Constant.URL_FIND_BANKUN_INFO+data_season_id);
                quarterVideo.setTitle(quarter_title);
                quarterVideo.setUrlInfo(Constant.URL_FIND_BANKUN_INFO + data_season_id);
                quarterVideo.setPic(previewUrl);
                quarterViewList.add(quarterVideo);
            }

            video.setQuarterVideoList(quarterViewList);
//            Gson gson = new Gson();
//            content = gson.toJson(video);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return video;
    }

    public static synchronized Video getDangumiInfo2(String bmId) {
        Video video = new Video();
        try {
            String videoUrl = String.format(Constant.URL_FIND_BANKUN_INFO_2, bmId);
            URL url = new URL(videoUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10 * 1000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200)
                throw new RuntimeException("请求url失败");
            InputStream is = conn.getInputStream();//拿到输入流
            String response = "";
            if ("gzip".equals(conn.getContentEncoding())) {
                response = StringUtil.readDataForZgip(is, "utf-8");
            } else {
                response = StringUtil.readDataForZgip(is);
            }
            conn.disconnect();
            if (response != null && !response.isEmpty()) {
                String str1 = "seasonListCallback(";
                String str2 = ");";
                String str3 = StringUtils.substringBetween(response, str1, str2);

                JSONObject json = new JSONObject(str3);
                int code = json.getInt("code");
                if (code == 0) {
                    JSONObject result = json.getJSONObject("result");
                    System.out.println(result.toString());
                    video.setTitle(result.getString("bangumi_title"));
                    video.setPic(result.getString("cover"));
                    video.setDescription(result.getString("evaluate"));

                    JSONArray tags = result.getJSONArray("tags");
                    String label = "";
                    for (int i = 0, num = tags.length(); i < num; i++) {
                        JSONObject item = tags.getJSONObject(i);
                        label += item.getString("tag_name") + " ";
                    }
                    video.setSbutitle(label);

                    JSONArray episodes = result.getJSONArray("episodes");
                    List<Video> episodesVideoList = new ArrayList<Video>();
                    for (int i = 0, num = episodes.length(); i < num; i++) {
                        Video vi = new Video();
                        JSONObject item = episodes.getJSONObject(i);
                        vi.setTitle(item.getString("index_title"));
                        vi.setAid(item.getString("episode_id"));
                        episodesVideoList.add(vi);
                    }
                    video.setBangumiVideoList(episodesVideoList);

                    JSONArray seasons = result.getJSONArray("seasons");
                    List<Video> seasonsViewList = new ArrayList<Video>();
                    for (int i = 0, num = seasons.length(); i < num; i++) {
                        Video vi = new Video();
                        JSONObject item = seasons.getJSONObject(i);
                        vi.setTitle(item.getString("title"));
                        //vi.setAid(item.getString("season_id"));
                        vi.setPic(item.getString("cover"));
                        vi.setBmId(item.getString("season_id"));
                        seasonsViewList.add(vi);
                    }
                    video.setQuarterVideoList(seasonsViewList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return video;
    }

    /**
     * 发现 - 番剧详情获取av
     */
    public static synchronized String getDangumiAV(String id) {
        String content = "";
        try {
            String av = id.split("#")[1];
            String videoUrl = String.format(Constant.URL_FIND_BANKUN_INFO_AV, av);
            //System.out.println(videoUrl);
            URL url = new URL(videoUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10 * 1000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200)
                throw new RuntimeException("请求url失败");
            InputStream is = conn.getInputStream();//拿到输入流
            String response = "";
            if ("gzip".equals(conn.getContentEncoding())) {
                response = StringUtil.readDataForZgip(is, "utf-8");
            } else {
                response = StringUtil.readDataForZgip(is);
            }
            conn.disconnect();
            if (response != null && !response.isEmpty()) {
                JSONObject json = new JSONObject(response);
                int code = json.getInt("code");
                if (code == 0) {
                    JSONObject result = json.getJSONObject("result");
                    JSONObject currentEpisode = result.getJSONObject("currentEpisode");
                    content = currentEpisode.getString("avId");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 搜索-视频
     */
    public static synchronized List<Video> getSearchVideo(String key, String page) {
        List<Video> videoList = new ArrayList<Video>();
        try {
            //System.out.println(Constant.URL_SEARCH_VIDEO + "&keyword="+URLEncoder.encode(key, "UTF-8")+"&page="+page);
            Document document = Jsoup.connect(Constant.URL_SEARCH_VIDEO + "&keyword=" + URLEncoder.encode(key, "UTF-8") + "&page=" + page).data("jquery", "java")
                    .userAgent("Mozilla").cookie("auth", "token")
                    .timeout(50000).get();
            String content = document.toString();
            if (content != null && !content.isEmpty()) {
                //System.out.println(document.toString());
                Elements so_wrap = document.getElementsByClass("ajax-render");
                //System.out.println(so_wrap.toString());
                Elements li = so_wrap.select("li");
                //System.out.println(li.size());
                for (int i = 0; i < li.size(); i++) {
                    Element item = li.get(i);
                    System.out.println(item.toString());
                    Video video = new Video();
                    Elements img = item.select("a[href]");
                    //Elements intro = item.select("p");
                    Elements icon_wrap = item.getElementsByClass("tags");
                    Elements span = icon_wrap.select("span");
                    //System.out.println(item.toString());
                    //System.out.println(icon_wrap.toString());
                    //System.out.println("------------");
                    String str1 = "/video/av";
                    //System.out.println(str1);
                    String str2 = "?from=search";
                    //System.out.println(str2);
                    String str3 = StringUtils.substringBetween(img.attr("href"), str1, str2);
                    video.setAid(str3);
                    //System.out.println(img.attr("href"));
                    //System.out.println(str3);
                    video.setTitle(img.attr("title"));
                    //System.out.println("title==="+img.attr("title"));
                    video.setPic(img.select("img").attr("abs:data-src"));
                    //System.out.println("src==="+img.select("img").attr("abs:data-src"));
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoList;
    }

    /**
     * 搜索-番剧
     */
    public static synchronized List<Video> getSearchBangunmi(String key, String page) {
        List<Video> videoList = new ArrayList<Video>();
        try {
            Document document = Jsoup.connect(Constant.URL_SEARCH_SERIES + "&keyword=" + URLEncoder.encode(key, "UTF-8") + "&page=" + page).data("jquery", "java")
                    .userAgent("Mozilla").cookie("auth", "token")
                    .timeout(50000).get();
            String content = document.toString();
            if (content != null && !content.isEmpty()) {
                Elements ajax_render = document.select("div.ajax-render");
                Elements left_img = document.select("div.left-img");
                Elements img = left_img.select("a");
                String imgurl = img.select("img").attr("abs:data-src");
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
                for (int i = 0; i < list.size(); i++) {
                    Video video = new Video();
                    Element item = list.get(i);
                    String videourl = item.attr("href");
                    String[] urls = videourl.split("\\?from");
                    if (urls.length > 1) {
                        videourl = urls[0];
                    }
                    String quarter = item.text();
                    //System.out.println(videourl);
                    //System.out.println(quarter);
                    video.setUrlInfo(videourl);
                    video.setPic(imgurl);
                    video.setTitle(title + quarter);
                    video.setDescription(des);
                    video.setUpdateContent("");

                    videoList.add(video);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoList;
    }

    public static synchronized JSONObject getPlayUrl(String av) {
        JSONObject content = new JSONObject();
        try {

            String u = String.format(Constant.URL_PLAY_VIDEO_INFO, av);
            System.out.println(u);
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Cookie", "buvid3=C4C5E5DA-AA7A-488F-90B6-1D9F6630601B12546infoc");
            conn.setConnectTimeout(10 * 1000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != 200)
                throw new RuntimeException("请求url失败");
            InputStream is = conn.getInputStream();//拿到输入流
            String s = "";
            if ("gzip".equals(conn.getContentEncoding())) {
                s = StringUtil.readDataForZgip(is, "utf-8");
            } else {
                s = StringUtil.readDataForZgip(is);
            }
            conn.disconnect();
            System.out.println(s);
            s = s.replace("jQuery172024279008170264427_1478221268354(", "");
            s = s.replace(");", "");
            if (s != null && !s.isEmpty()) {
                JSONObject json = new JSONObject(s);
                if (json.isNull("code")) {
                    //System.out.println("code");
                    String mp4url = "";
                    String cid = json.getString("cid");
                    String img = json.getString("img");
                    JSONArray durl = json.getJSONArray("durl");
                    for (int i = 0, num = durl.length(); i < num; i++) {
                        JSONObject item = durl.getJSONObject(i);
                        mp4url = item.getString("url");
                    }

//                    JSONObject j = new JSONObject();
                    content.put("cid", cid);
                    content.put("url", mp4url);
                    content.put("img", img);
//                    content = j.toString();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }


}
