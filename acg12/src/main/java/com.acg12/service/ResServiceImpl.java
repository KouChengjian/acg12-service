package com.acg12.service;

import com.acg12.entity.po.Album;
import com.acg12.entity.po.Palette;
import com.acg12.entity.dto.Video;
import com.acg12.service.base.ResService;
import com.acg12.utils.http.ResRequest;
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

    /**
     * --------------------------------------自定义资源-------------------------------------------
     */
    @Cacheable(value = "resource_cache" , key = "'homeContent'")
    @Override
    public JSONObject getIndex() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cover","http://139.196.46.40:8080/res/images/weixin20180125141034.png");

            JSONArray jsonArray = new JSONArray();
            JSONObject item1 = new JSONObject();
            item1.put("title","社畜");
            item1.put("cover","http://139.196.46.40:8080/res/images/a9712e66adddc2a5c0aa8.png");
            jsonArray.put(item1);

            JSONObject item2 = new JSONObject();
            item2.put("title","铁轨");
            item2.put("cover","http://139.196.46.40:8080/res/images/18012514102.png");
            jsonArray.put(item2);

            JSONObject item3 = new JSONObject();
            item3.put("title","夏娜");
            item3.put("cover","http://139.196.46.40:8080/res/images/7c1ed21b0ef41bd552c80d.png");
            jsonArray.put(item3);

            JSONObject item4 = new JSONObject();
            item4.put("title","萌萌哒");
            item4.put("cover","http://139.196.46.40:8080/res/images/a5d253eb0881bd418d88.png");
            jsonArray.put(item4);
            jsonObject.put("list" , jsonArray);

            return jsonObject;
        }catch (Exception e) {
            System.err.println("ResServiceImpl->getHomeContent()"
                    +e.toString());
        }
        return null;
    }

    /**
     * --------------------------------------花瓣网资源-------------------------------------------
     */
    @Cacheable(value = "resource_cache" , key = "'albumList_max=' + #max")
    @Override
    public JSONObject getAlbumList(String max) {
        List<Album> albumList  = ResRequest.getAlbumList(max);
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
        List<Palette> paletteList  = ResRequest.getPaletteList(max);
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
        List<Album> albumList  = ResRequest.getBoardsToAlbumList(boardId , max);
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

    @Cacheable(value = "resource_cache" , key = "'searchAlbum_key=' + #key + ',Page=' + #page")
    @Override
    public JSONArray getSearchAlbum(String key, String page) {
        return ResRequest.getSearchAlbum(key , page);
    }

    @Cacheable(value = "resource_cache" , key = "'searchBoards_key=' + #key + ',Page=' + #page")
    @Override
    public JSONArray getSearchBoards(String key, String page) {
        return ResRequest.getSearchPalette(key , page);
    }

    /**
     * --------------------------------------动漫之家资源--------------------------------------------
     */

    @Override
    public JSONArray getNews(String pager) {
        return ResRequest.getNewList(pager);
    }

    /**
     * --------------------------------------萌娘百科资源--------------------------------------------
     */
    @Cacheable(value = "resource_cache" , key = "'dangumiList_page=' + #page")
    @Override
    public JSONArray getSearchKeyList(String key) {
        return ResRequest.getSearchKeyList(key);
    }


    /**
     * --------------------------------------bilibili资源--------------------------------------------
     */
    @Cacheable(value = "resource_cache" , key = "'videoTypeList_type=' + #type + ',page=' + #page")
    @Override
    public JSONObject getVideoTypeList(String type, String page) {
        String url = StringUtil.getMoreVideoUrl(type);
        List<Video> videoList = ResRequest.getVideoTypeList(url , page);
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
        Video video = ResRequest.getVideoTypeInfo(av);
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
        List<Video> videoList = ResRequest.getDangumiList(page);
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
        Video video = ResRequest.getDangumiInfo2(bmId);
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
        String av = ResRequest.getDangumiAV(id);
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

    @Cacheable(value = "resource_cache" , key = "'searchVideo_key=' + #key + ',Page=' + #page")
    @Override
    public JSONObject getSearchVideo(String key, String page) {
        List<Video> paletteList = ResRequest.getSearchVideo(key , page);
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
        List<Video> paletteList = ResRequest.getSearchBangunmi(key , page);
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
        JSONObject content = ResRequest.getPlayUrl(av);
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
