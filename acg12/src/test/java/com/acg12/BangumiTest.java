package com.acg12;

import com.acg12.modules.app.dao.BangumiDao;
import com.acg12.factory.ConnectionFactory;
import com.acg12.common.utils.crawler.http.ResRequest;
import com.acg12.common.utils.StringUtil;
import com.acg12.modules.app.entity.dto.Video;
import com.acg12.modules.app.entity.po.Bangumi;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/15.
 */
public class BangumiTest {

    public static void main(String[] args) {
//        String s;
//        HashMap<String , Object> map = new HashMap<String, Object>();
//        map.put("sss" , new Object());

//        getBangumiList();
//        initDB();
//        try {
//            new BangumiUtil().getBangumiList();
////            new BangumiUtil().getVideoInfo(null , "115138");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        System.err.println(ResRequest.getNewList("0").toString());

        try {
            String title = URLEncoder.encode("夏娜", "UTF-8");
//            System.err.println(title);
//            ResRequest.getSearchKeyList(title);
            ResRequest.getSearchKeyInfo(title);
//            JSONArray j =  ResRequest.getSearchPalette(title, 1+"");
//            System.err.println(j.length()+"==");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void initDB() {
        BangumiDao bangumiDao = ConnectionFactory.getMapper(BangumiDao.class);
        List<Bangumi> bangumis = new ArrayList<Bangumi>();
        Bangumi bangumi = new Bangumi();
//        bangumi.setPlatform("sssss");
        bangumi.setTitle("sssss");
        bangumis.add(bangumi);
        bangumis.add(bangumi);
        bangumiDao.insertList(bangumis);
        ConnectionFactory.commit();
    }

    private static void getBangumiList(){
        BangumiDao bangumiDao = ConnectionFactory.getMapper(BangumiDao.class);

        String u = "https://bangumi.bilibili.com/web_api/season/index_global?page=1&page_size=20&version=0&is_finish=0&start_year=2011&tag_id=&index_type=1&index_sort=0&quarter=0";
        try {
            List<Bangumi> bangumis = new ArrayList<Bangumi>();
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            InputStream inStream = conn.getInputStream();
            String content = "";
            if (conn.getContentEncoding() == null) {
            }
            if (conn.getContentEncoding() != null && conn.getContentEncoding().equals("gzip")) {
                content = StringUtil.readDataForZgip(inStream, "utf-8");
            } else {
                content = StringUtil.readDataForZgip(inStream);
            }
            inStream.close();
            conn.disconnect();
            if (content != null && !content.isEmpty()) {
//                System.out.println(content);
                JSONObject jsonObject = new JSONObject(content);
                JSONObject result = jsonObject.getJSONObject("result");
                JSONArray array = result.getJSONArray("list");

                for (int i = 0, num = array.length(); i < num; i++) {
                    System.out.println(array.get(i).toString());
                    JSONObject item = array.getJSONObject(i);
                    Bangumi bangumi = new Bangumi();
                    bangumi.setPlatform("bilibili");
                    bangumi.setTitle(item.getString("title"));
                    bangumi.setCover(item.getString("cover"));
                    bangumi.setNowStatus(item.getString("is_finish"));
                    bangumi.setTotalCount(item.getString("total_count"));
                    bangumi.setSerializeWeek(item.getString("week"));
                    bangumi.setSerializeTime(item.getInt("update_time"));
                    bangumi.setStartPlayTime(item.getInt("pub_time"));

                    Video video = ResRequest.getDangumiInfo(item.getString("season_id"));
                    System.out.println(video.toString());
                    bangumi.setTags(video.getSbutitle());
                    bangumi.setIntro(video.getDescription());
                    bangumiDao.insert(bangumi);
                    ConnectionFactory.commit();
                    System.out.println("objectid = " +bangumi.getBangumitId());
//                    bangumis.add(bangumi);
                }
            }

//            bangumiDao.insertList(bangumis);
//            ConnectionFactory.commit();

        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}
