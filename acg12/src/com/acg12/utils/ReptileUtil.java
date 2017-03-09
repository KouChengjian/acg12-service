package com.acg12.utils;

import com.acg12.beans.Album;
import com.acg12.beans.Palette;
import com.acg12.beans.Video;
import com.acg12.config.Constant;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kouchengjian on 2017/3/7.
 */
@Service("ReptileUtil")
public class ReptileUtil {

    @Cacheable(value = "myCache")
    public static synchronized JSONObject getHomeContent(){
        System.out.println("s1");
        List<Video> bannerList = HttpUtil.getBanner();
        System.out.println("s2");
        List<Album> albumList  = HttpUtil.getAlbumHtmlString("");
        List<Palette> paletteList = HttpUtil.getPaletteHtmlString("");
        List<List<Video>> videoLl = HttpUtil.getHomeHtmlString();
        List<Video> bangumiList = null;
        List<Video> dougaList = null;
        for(int i = 0 ; i < videoLl.size() ; i++){
            List<Video> items = videoLl.get(i);
            if(i == 0){
                bangumiList = items;
            }else if(i == 1){
                dougaList = items;
            }
        }

        try {
            Gson gson = new Gson();
            JSONArray bannerJson = new JSONArray(gson.toJson(bannerList));
            JSONArray albumJson = new JSONArray(gson.toJson(albumList));
            JSONArray paletteJson = new JSONArray(gson.toJson(paletteList));
            JSONArray bangumiJson = new JSONArray(gson.toJson(bangumiList));
            JSONArray dougaJson = new JSONArray(gson.toJson(dougaList));

            JSONObject array = new JSONObject();
            array.put("banner",bannerJson);
            array.put("album",albumJson);
            array.put("palette",paletteJson);
            array.put("bangumi",bangumiJson);
            array.put("douga",dougaJson);

//            JSONObject json = new JSONObject();
//            json.put("result", 200);
//            json.put("desc",   "获取成功");
//            json.put("data",   array);
//            System.err.println(array.toString());

            return array;
        }catch (Exception e) {
            System.err.println(e.toString());
        }

        return null;
    }

    @Cacheable(value="myCache", key="#id")
    public static String getUsernameById(int id){
        System.out.println("调用了测试缓存的方法");
        System.out.println("数据库中查到此用户号[" + id + "]对应的用户名为[" + "admin" + "]");
        return "admin";
    }
}
