package com.acg12.service;

import com.acg12.entity.dto.Acg12AlbumDto;
import com.acg12.entity.dto.Acg12PaletteDto;

import java.util.List;

/**
 * Created by kouchengjian on 2017/3/9.
 */
public interface Acg12ResourceService {

    /**
     * --------------------------------------自定义资源-------------------------------------------
     */
//    JSONObject getIndex();

//    IndexDto getIndex();

    /**
     * --------------------------------------花瓣网资源-------------------------------------------
     */
    List<Acg12AlbumDto> getHuaBanImages(String max);

    List<Acg12PaletteDto> getHuaBanBoards(String max);

    List<Acg12AlbumDto> getHuaBanBoardsToImages(String boardId, String max);

    List<Acg12AlbumDto> getHuaBanSearchImages(String key, String page);

    List<Acg12PaletteDto> getHuaBanSearchBoards(String key, String page);

    /**
     * --------------------------------------动漫之家资源--------------------------------------------
     */
    String getDongManZhiJiaNews(String pager);


    /**
     * --------------------------------------萌娘百科资源--------------------------------------------
     */
//    JSONArray getMoeGirlSearchKeyList(String key);
//
//    JSONArray gettMoeGirlSearchKeyInfo(String key);

    /**
     * --------------------------------------番组计划资源--------------------------------------------
     */
    // 搜索
//    JSONObject getBgmSearchKeyList(String key);

//    JSONObject getBgmSubjectInfo(int sId, int type, String key);

    // 每日更新
    String getBgmCalendarList();

//    CharacterInfoDto getBgmCharacterInfo(int cId);
//
//    SubjectInfoDto getBgmSubjectInfo(int sId);

    /**
     * --------------------------------------bilibili资源--------------------------------------------
     */
//    JSONObject getVideoTypeList(String type, String page);
//
//    JSONObject getVideoTypeInfo(String av);
//
//    JSONObject getDangumiList(String page);
//
//    JSONObject getDangumiInfo(String av);
//
//    JSONObject getDangumiAV(String id);
//
//    JSONObject getSearchVideo(String key, String page);
//
//    JSONObject getSearchDangumi(String key, String page);
//
//    JSONObject getPlayInfo(String av);


}
