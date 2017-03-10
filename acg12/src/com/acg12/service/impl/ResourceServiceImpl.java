package com.acg12.service.impl;

import com.acg12.beans.Album;
import com.acg12.beans.Palette;
import com.acg12.beans.Video;
import com.acg12.service.ResourceService;
import com.acg12.utils.HttpUtil;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kouchengjian on 2017/3/9.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Cacheable(value = "resource_cache" , key = "'homeContent'")
    @Override
    public JSONObject getIndex() {
        System.out.println("s1");
        List<Video> bannerList = HttpUtil.getBanner();
        System.out.println("s2");
        List<Album> albumList  = HttpUtil.getAlbumList("");
        List<Palette> paletteList = HttpUtil.getPaletteList("");
        List<List<Video>> videoLl = HttpUtil.getHomeLists();
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
            System.err.println("ResourceServiceImpl->getHomeContent"
                    +e.toString());
        }

        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'albumList_max=' + #max")
    @Override
    public JSONObject getAlbumList(String max) {
        List<Album> albumList  = HttpUtil.getAlbumList(max);
        try {
            Gson gson = new Gson();
            JSONArray albumJson = new JSONArray(gson.toJson(albumList));
            JSONObject array = new JSONObject();
            array.put("album",albumJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResourceServiceImpl->getAlbumList"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'paletteList_max=' + #max")
    @Override
    public JSONObject getBoardsList(String max) {
        List<Palette> paletteList  = HttpUtil.getPaletteList(max);
        try {
            Gson gson = new Gson();
            JSONArray paletteJson = new JSONArray(gson.toJson(paletteList));
            JSONObject array = new JSONObject();
            array.put("palette",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResourceServiceImpl->getBoardsList"
                    +e.toString());
        }
        return null;
    }


}
