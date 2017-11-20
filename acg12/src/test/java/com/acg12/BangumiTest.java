package com.acg12;

import com.acg12.conf.Constant;
import com.acg12.dao.BangumiDao;
import com.acg12.entity.po.Bangumi;
import com.acg12.entity.po.Video;
import com.acg12.factory.ConnectionFactory;
import com.acg12.utils.StringUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/15.
 */
public class BangumiTest {

    public static void main(String[] args) {
//        String u= "https://bangumi.bilibili.com/web_api/season/index_global?page=1&page_size=20" +
//                "&version=0&is_finish=0&start_year=2011&tag_id=&index_type=1&index_sort=0&quarter=0";
//        try {
//            URL url = new URL(u);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setConnectTimeout(5 * 1000);
//            conn.setRequestMethod("GET");
//            InputStream inStream = conn.getInputStream();
//            String content = "";
//            if (conn.getContentEncoding().equals("gzip")) {
//                System.out.print("gzip");
//                content = StringUtil.readDataForZgip(inStream, "utf-8");
//            }else{
//                System.out.print(conn.getContentEncoding());
//                content = StringUtil.readDataForZgip(inStream);
//            }
//            conn.disconnect();
//            if(content != null && !content.isEmpty()){
//                System.out.print(content);
////                JSONObject bannerjson = new JSONObject(content);
////                JSONArray array = bannerjson.getJSONArray("result");
////                for (int i=0 , num = array.length() ; i < num ; i++) {
////                    Video item = new Video();
////                    item.setPic(array.getJSONObject(i).getString("img").toString());
////                    item.setTitle(array.getJSONObject(i).getString("title").toString());
////                    item.setUrlInfo(array.getJSONObject(i).getString("link").toString());
////                    bannerList.add(item);
////                }
////                System.out.println(new Gson().toJson(bannerList));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        initDB();
    }

    private static void initDB() {
        BangumiDao bangumiDao = ConnectionFactory.getMapper(BangumiDao.class);
        List<Bangumi> bangumis = new ArrayList<Bangumi>();
        Bangumi bangumi = new Bangumi();
        bangumi.setPlatform("sssss");
        bangumi.setTitle("sssss");
        bangumis.add(bangumi);
        bangumis.add(bangumi);
        bangumiDao.insertList(bangumis);
        ConnectionFactory.commit();
    }
}
