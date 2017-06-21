package com.acg12.service;

import com.acg12.beans.Album;
import com.acg12.beans.Palette;
import com.acg12.beans.Video;
import com.acg12.service.base.ResService;
import com.acg12.utils.HttpUtil;
import com.acg12.utils.StringUtil;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by kouchengjian on 2017/3/9.
 */
public class ResServiceImpl implements ResService {

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

            JSONObject object = new JSONObject();
            object.put("banner",bannerJson);
            object.put("album",albumJson);
            object.put("palette",paletteJson);
            object.put("bangumi",bangumiJson);
            object.put("douga",dougaJson);
            return object;
        }catch (Exception e) {
            System.err.println("ResServiceImpl->getHomeContent()"
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
            System.err.println("ResServiceImpl->getAlbumList()"
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
            System.err.println("ResServiceImpl->getBoardsList()"
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
            System.err.println("ResServiceImpl->getBoardsToAlbumList()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'videoTypeList_type=' + #type + ',page=' + #page")
    @Override
    public JSONObject getVideoTypeList(String type, String page) {
        String url = StringUtil.getMoreVideoUrl(type);
        List<Video> videoList = HttpUtil.getVideoTypeList(url , page);
        try {
            Gson gson = new Gson();
            JSONArray paletteJson = new JSONArray(gson.toJson(videoList));
            JSONObject array = new JSONObject();
            array.put("video",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getVideoTypeList()"
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
            System.err.println("ResServiceImpl->getVideoTypeInfo()"
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
            System.err.println("ResServiceImpl->getDangumiList()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'dangumiInfo_bmId=' + #bmId")
    @Override
    public JSONObject getDangumiInfo(String bmId) {
        Video video = HttpUtil.getDangumiInfo2(bmId);
        try {
            Gson gson = new Gson();
            JSONObject paletteJson = new JSONObject(gson.toJson(video));
            JSONObject array = new JSONObject();
            array.put("video",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getDangumiInfo()"
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
            System.err.println("ResServiceImpl->getDangumiAV()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'searchAlbum_key=' + #key + ',Page=' + #page")
    @Override
    public JSONObject getSearchAlbum(String key, String page) {
        List<Album> albumList = HttpUtil.getSearchAlbum(key , page);
        System.err.println(albumList.size());
        try {
            Gson gson = new Gson();
            JSONArray albumJson = new JSONArray(gson.toJson(albumList));
            JSONObject array = new JSONObject();
            array.put("album",albumJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getSearchAlbum()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'searchBoards_key=' + #key + ',Page=' + #page")
    @Override
    public JSONObject getSearchBoards(String key, String page) {
        List<Palette> paletteList = HttpUtil.getSearchPalette(key , page);
        try {
            Gson gson = new Gson();
            JSONArray paletteJson = new JSONArray(gson.toJson(paletteList));
            JSONObject array = new JSONObject();
            array.put("palette",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getSearchBoards()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'searchVideo_key=' + #key + ',Page=' + #page")
    @Override
    public JSONObject getSearchVideo(String key, String page) {
        List<Video> paletteList = HttpUtil.getSearchVideo(key , page);
        try {
            Gson gson = new Gson();
            JSONArray paletteJson = new JSONArray(gson.toJson(paletteList));
            JSONObject array = new JSONObject();
            array.put("video",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getSearchVideo()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'searchDangumi_key=' + #key + ',Page=' + #page")
    @Override
    public JSONObject getSearchDangumi(String key, String page) {
        List<Video> paletteList = HttpUtil.getSearchBangunmi(key , page);
        try {
            Gson gson = new Gson();
            JSONArray paletteJson = new JSONArray(gson.toJson(paletteList));
            JSONObject array = new JSONObject();
            array.put("video",paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getSearchDangumi()"
                    +e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache" , key = "'playInfo_av=' + #av")
    @Override
    public JSONObject getPlayInfo(String av) {
        JSONObject content = HttpUtil.getPlayUrl(av);
        try {
            JSONObject array = new JSONObject();
            array.put("info",content);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getPlayInfo()"
                    +e.toString());
        }
        return null;
    }


}
