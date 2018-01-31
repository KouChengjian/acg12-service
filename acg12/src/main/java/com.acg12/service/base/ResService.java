package com.acg12.service.base;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by kouchengjian on 2017/3/9.
 */
public interface ResService {

    /**
     * --------------------------------------自定义资源-------------------------------------------
     */
    JSONObject getIndex();

    /**
     * --------------------------------------花瓣网资源-------------------------------------------
     */
    JSONObject getAlbumList(String max);

    JSONObject getBoardsList(String max);

    JSONObject getBoardsToAlbumList(String boardId, String max);

    JSONArray getSearchAlbum(String key, String page);

    JSONArray getSearchBoards(String key, String page);


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
