package com.acg12.utils;

import com.acg12.dao.BangumiDao;
import com.acg12.dao.TagDao;
import com.acg12.dao.VideoDao;
import com.acg12.entity.po.Bangumi;
import com.acg12.entity.po.Tag;
import com.acg12.entity.po.Video;
import com.acg12.factory.ConnectionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/28.
 */
public class BangumiUtil {

    public String requestUrl(String urls) throws Exception {
        URL url = new URL(urls);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();
        String content = StringUtil.readDataForZgip(inStream);
        inStream.close();
        conn.disconnect();
        return content;
    }

    public void getVideoInfo(Video video , String id) throws Exception{
        String url = "https://bangumi.bilibili.com/web_api/episode/%s.json";
        String curl = "https://bangumi.bilibili.com/player/web_api/bangumi/html5?cid=%s";

        url = String.format(url , id);
        String content = requestUrl(url);
        if (content == null && content.isEmpty()) {
            return;
        }
        JSONObject jsonObject = new JSONObject(content);
        JSONObject result = jsonObject.getJSONObject("result");
        JSONObject currentEpisode = result.getJSONObject("currentEpisode");
        String danmaku = currentEpisode.getString("danmaku");

        curl = String.format(curl , danmaku);
        content = requestUrl(curl);
        jsonObject = new JSONObject(content);
        System.out.println(content);
        video.setDanmaku(jsonObject.getString("cid"));
//        video.setBilibiliUrl(jsonObject.getString("src"));
    }

    public String savaTags(JSONArray jsonArray) throws Exception {
        TagDao tagDao = ConnectionFactory.getMapper(TagDao.class);
        List<Tag> tagList = tagDao.queryList();
        String tags = "";
        for (int i = 0, total = jsonArray.length(); i < total; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String tag_name = jsonObject.getString("tag_name");
            boolean isExist = false;
            for (int j = 0, num = tagList.size(); j < num; j++) {
                Tag tag = tagList.get(j);
                if (tag.getTag().equals(tag_name)) {
                    isExist = true;
                    if (i == 0) {
                        tags = tag.getTagId() + "";
                    } else {
                        tags = tags + "," + tag.getTagId();
                    }
                }
            }

            if (!isExist) {
                Tag tag = new Tag();
                tag.setTag(tag_name);
                tagDao.insert(tag);
                ConnectionFactory.commit();
                if (i == 0) {
                    tags = tag.getTagId() + "";
                } else {
                    tags = tags + "," + tag.getTagId();
                }
            }
        }
        return tags;
    }

    public void getBangumiInfo(BangumiDao bangumiDao, Bangumi bangumi, String season_id) throws Exception {
        String bangumiInfoUrl = "http://bangumi.bilibili.com/jsonp/seasoninfo/%s.ver?callback=seasonListCallback&jsonp=jsonp&_=1511244854098";
        bangumiInfoUrl = String.format(bangumiInfoUrl, season_id);
        String contentInfo = requestUrl(bangumiInfoUrl);
        contentInfo = contentInfo.replace("seasonListCallback(", "");
        contentInfo = contentInfo.replace(");", "");
        if (contentInfo != null && !contentInfo.isEmpty()) {
            JSONObject jsonObject = new JSONObject(contentInfo);
            int code = jsonObject.getInt("code");
            if (code != 0) {
                return;
            }
            JSONObject result = jsonObject.getJSONObject("result");
            bangumi.setIntro(result.getString("evaluate"));
            String tags = savaTags(result.getJSONArray("tags"));
            bangumi.setTags(tags);
            if (bangumi.getBangumitId() == 0){
                bangumiDao.insert(bangumi);
            } else {
                bangumiDao.update(bangumi);
            }
            ConnectionFactory.commit();

            VideoDao videoDao = ConnectionFactory.getMapper(VideoDao.class);
            List<Video> videoList = videoDao.queryByBangumiId(bangumi.getBangumitId());

            JSONArray jsonArray = result.getJSONArray("episodes");
            for (int i = 0 , total = jsonArray.length() ; i < total ; i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                String index = item.getString("index");
                String index_title = item.getString("index_title");
                Video video = null;

                for (int j = 0, num = videoList.size(); j < num; j++) {
                    Video vi = videoList.get(j);
                    if (vi.getIndexNum().equals(index) && vi.getIndexTitle().equals(index_title)) {
                        video = vi;
                        video.updateTime();
                    }
                }
                if(video == null){
                    video = new Video();
                }

                video.setBangumitId(bangumi.getBangumitId());
                video.setCover(item.getString("cover"));
                video.setIndexNum(item.getString("index"));
                video.setIndexTitle(item.getString("index_title"));
                getVideoInfo(video , item.getString("episode_id"));

                if (video.getvId() == 0){
                    videoDao.insert(video);
                } else {
                    videoDao.update(video);
                }
                ConnectionFactory.commit();

            }
        }
    }

    public void getBangumiList(String time, String pages) throws Exception {
        String bangumiUrl = String.format("https://bangumi.bilibili.com/web_api/season/index_global?page=1&page_size=%s&version=0&is_finish=0&start_year=%s&tag_id=&index_type=1&index_sort=0&quarter=0", pages, time);

        BangumiDao bangumiDao = ConnectionFactory.getMapper(BangumiDao.class);
        List<Bangumi> bangumis = bangumiDao.queryList();

        String content = requestUrl(bangumiUrl);
        if (content == null && content.isEmpty()) {
            return;
        }

        JSONObject jsonObject = new JSONObject(content);
        JSONObject result = jsonObject.getJSONObject("result");
        JSONArray array = result.getJSONArray("list");

        for (int i = 0, num = array.length(); i < num; i++) {
            JSONObject item = array.getJSONObject(i);
            Bangumi bangumi = null;
            String title = item.getString("title");
            for (int j = 0, total = bangumis.size(); j < total; j++) {
                Bangumi bangu = bangumis.get(j);
                if (bangu.getTitle().equals(title)) {
                    bangumi = bangu;
                    bangumi.updateTime();
                }
            }
            if (bangumi == null) {
                bangumi = new Bangumi();
            }
            bangumi.setPlatform("bilibili");
            bangumi.setTitle(item.getString("title"));
            bangumi.setCover(item.getString("cover"));
            bangumi.setNowStatus(item.getString("is_finish"));
            bangumi.setTotalCount(item.getString("total_count"));
            bangumi.setSerializeWeek(item.getString("week"));
            bangumi.setSerializeTime(item.getInt("update_time"));
            bangumi.setStartPlayTime(item.getInt("pub_time"));

            // 获取标签 内容简介 集数
            getBangumiInfo(bangumiDao, bangumi, item.getString("season_id"));

        }
    }

    public void getBangumiList() throws Exception {
        getBangumiList("2011", "20");
    }

}
