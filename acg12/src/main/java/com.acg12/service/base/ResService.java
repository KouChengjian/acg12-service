package com.acg12.service.base;

import com.acg12.entity.po.Album;
import com.acg12.entity.po.Palette;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by kouchengjian on 2017/3/9.
 */
public interface ResService {

    /**
     * --------------------------------------花瓣网资源-------------------------------------------
     */
    List<Album> getHuaBanImages(String max);

    List<Palette> getHuaBanBoards(String max);

    List<Album> getHuaBanBoardsToImages(String boardId, String max);

    List<Album> getHuaBanSearchImages(String key, String page);

    List<Palette> getHuaBanSearchBoards(String key, String page);

    /**
     * --------------------------------------自定义资源-------------------------------------------
     */
    JSONObject getIndex();




    /**
     * --------------------------------------动漫之家资源--------------------------------------------
     */
    JSONArray getNews(String pager);


    /**
     * --------------------------------------萌娘百科资源--------------------------------------------
     */
    JSONArray getSearchKeyList(String key);

    /**
     * --------------------------------------bilibili资源--------------------------------------------
     */
    JSONObject getVideoTypeList(String type, String page);

    JSONObject getVideoTypeInfo(String av);

    JSONObject getDangumiList(String page);

    JSONObject getDangumiInfo(String av);

    JSONObject getDangumiAV(String id);

    JSONObject getSearchVideo(String key, String page);

    JSONObject getSearchDangumi(String key, String page);

    JSONObject getPlayInfo(String av);






}
