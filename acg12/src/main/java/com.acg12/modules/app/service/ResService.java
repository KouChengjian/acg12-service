package com.acg12.modules.app.service;

import com.acg12.modules.app.entity.dto.IndexDto;
import com.acg12.modules.app.entity.po.Album;
import com.acg12.modules.app.entity.po.Palette;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kouchengjian on 2017/3/9.
 */
public interface ResService {

    /**
     * --------------------------------------自定义资源-------------------------------------------
     */
//    JSONObject getIndex();

    IndexDto getIndex();

    /**
     * --------------------------------------花瓣网资源-------------------------------------------
     */
    List<Album> getHuaBanImages(String max);

    List<Palette> getHuaBanBoards(String max);

    List<Album> getHuaBanBoardsToImages(String boardId, String max);

    List<Album> getHuaBanSearchImages(String key, String page);

    List<Palette> getHuaBanSearchBoards(String key, String page);

    /**
     * --------------------------------------动漫之家资源--------------------------------------------
     */
    JSONArray getNews(String pager);


    /**
     * --------------------------------------萌娘百科资源--------------------------------------------
     */
    JSONArray getMoeGirlSearchKeyList(String key);

    JSONArray gettMoeGirlSearchKeyInfo(String key);

    /**
     * --------------------------------------番组计划资源--------------------------------------------
     */
    JSONObject getBgmSearchKeyList(String key);

    JSONObject getBgmSubjectInfo(int sId, int type, String key);

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
