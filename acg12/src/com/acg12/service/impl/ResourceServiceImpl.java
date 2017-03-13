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
            System.err.println("ResourceServiceImpl->getHomeContent()"
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
            System.err.println("ResourceServiceImpl->getAlbumList()"
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
            System.err.println("ResourceServiceImpl->getBoardsList()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'paletteAlbumList_boardId=' + #boardId +',max=' + #max")
    @Override
    public JSONObject getBoardsToAlbumList(String boardId, String max) {
        List<Album> albumList  = HttpUtil.getBoardsToAlbumList(boardId , max);
        try {
            Gson gson = new Gson();
            JSONArray paletteJson = new JSONArray(gson.toJson(albumList));
            JSONObject array = new JSONObject();
            array.put("album",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResourceServiceImpl->getBoardsToAlbumList()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'videoTypeList_type=' + #type + ',page=' + #page")
    @Override
    public JSONObject getVideoTypeList(String type, String page) {
        List<Video> videoList = HttpUtil.getVideoTypeList(type , page);
        try {
            Gson gson = new Gson();
            JSONArray paletteJson = new JSONArray(gson.toJson(videoList));
            JSONObject array = new JSONObject();
            array.put("video",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResourceServiceImpl->getVideoTypeList()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'videoTypeInfo_av=' + #av")
    @Override
    public JSONObject getVideoTypeInfo(String av) {
        Video video = HttpUtil.getVideoTypeInfo(av);
        try {
            Gson gson = new Gson();
            JSONObject paletteJson = new JSONObject(gson.toJson(video));
            JSONObject array = new JSONObject();
            array.put("video",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResourceServiceImpl->getVideoTypeInfo()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'dangumiList_page=' + #page")
    @Override
    public JSONObject getDangumiList(String page) {
        List<Video> videoList = HttpUtil.getDangumiList(page);
        try {
            Gson gson = new Gson();
            JSONArray paletteJson = new JSONArray(gson.toJson(videoList));
            JSONObject array = new JSONObject();
            array.put("video",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResourceServiceImpl->getDangumiList()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'dangumiInfo_av=' + #av")
    @Override
    public JSONObject getDangumiInfo(String av) {
        Video video = HttpUtil.getDangumiInfo(av);
        try {
            Gson gson = new Gson();
            JSONObject paletteJson = new JSONObject(gson.toJson(video));
            JSONObject array = new JSONObject();
            array.put("video",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResourceServiceImpl->getDangumiInfo()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'dangumiAV_id=' + #id")
    @Override
    public JSONObject getDangumiAV(String id) {
        String av = HttpUtil.getDangumiAV(id);
        try {
            JSONObject array = new JSONObject();
            array.put("av",av);
            return array;
        } catch (Exception e) {
            System.err.println("ResourceServiceImpl->getDangumiAV()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'searchAlbum_key=' + #key + ',Page=' + #Page")
    @Override
    public JSONObject getSearchAlbum(String key, String Page) {
        List<Album> albumList = HttpUtil.getSearchAlbum(key , Page);
        try {
            Gson gson = new Gson();
            JSONObject paletteJson = new JSONObject(gson.toJson(albumList));
            JSONObject array = new JSONObject();
            array.put("album",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResourceServiceImpl->getSearchAlbum()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'searchBoards_key=' + #key + ',Page=' + #Page")
    @Override
    public JSONObject getSearchBoards(String key, String Page) {
        List<Palette> paletteList = HttpUtil.getSearchPalette(key , Page);
        try {
            Gson gson = new Gson();
            JSONObject paletteJson = new JSONObject(gson.toJson(paletteList));
            JSONObject array = new JSONObject();
            array.put("palette",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResourceServiceImpl->getSearchBoards()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'searchVideo_key=' + #key + ',Page=' + #Page")
    @Override
    public JSONObject getSearchVideo(String key, String Page) {
        List<Video> paletteList = HttpUtil.getSearchVideo(key , Page);
        try {
            Gson gson = new Gson();
            JSONObject paletteJson = new JSONObject(gson.toJson(paletteList));
            JSONObject array = new JSONObject();
            array.put("video",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResourceServiceImpl->getSearchVideo()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'searchDangumi_key=' + #key + ',Page=' + #Page")
    @Override
    public JSONObject getSearchDangumi(String key, String Page) {
        List<Video> paletteList = HttpUtil.getSearchBangunmi(key , Page);
        try {
            Gson gson = new Gson();
            JSONObject paletteJson = new JSONObject(gson.toJson(paletteList));
            JSONObject array = new JSONObject();
            array.put("video",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResourceServiceImpl->getSearchDangumi()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'playInfo_av=' + #av")
    @Override
    public JSONObject getPlayInfo(String av) {
        String content = HttpUtil.getPlayUrl(av);
        try {
            JSONObject array = new JSONObject();
            array.put("info",content);
            return array;
        } catch (Exception e) {
            System.err.println("ResourceServiceImpl->getPlayInfo()"
                    +e.toString());
        }
        return null;
    }


}
