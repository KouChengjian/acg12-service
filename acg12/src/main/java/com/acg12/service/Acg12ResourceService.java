package com.acg12.service;

import com.acg12.entity.dto.*;
import com.acg12.entity.po.Acg12CharacterEntity;

import java.util.List;

/**
 * Created by kouchengjian on 2017/3/9.
 */
public interface Acg12ResourceService {

    /**
     * --------------------------------------自定义资源-------------------------------------------
     */
//    JSONObject getIndex();

//    Acg12IndexDto getIndex();

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
    String getMengNiangSearchKeyList(String key);

    String gettMoeGirlSearchKeyInfo(String key);

    /**
     * --------------------------------------番组计划资源--------------------------------------------
     */
    // 搜索
    String getBgmSearchKeyList(String key);

    // 每日更新
    String getBgmCalendarList();

    Acg12SubjectDto getBgmSubject(int sId);

    Acg12PersonDto getBgmPerson(int pId);

    Acg12CharacterDto getBgmCharacter(int cId);

    /**
     * --------------------------------------酷克漫画资源-------------------------------------------
     */
    List<Acg12CaricatureDto> kukeSearch(String key);

    Acg12CaricatureDto kukeCaricatureInfo(int id);

    Acg12CaricatureChaptersDto kukeCaricatureChapters(int id, int index);

    /**
     * --------------------------------------bilibili资源--------------------------------------------
     */
    List<Acg12VideoDto> getBilibiliVideoTypeList(String type, String page);

    Acg12VideoDto getBilibiliVideoTypeInfo(String av);

    List<Acg12VideoDto> getBilibiliDangumiList(String page);

    Acg12VideoDto getBilibiliDangumiInfo(String av);

    String getBilibiliDangumiAV(String id);

    List<Acg12VideoDto> getBilibiliSearchVideo(String key, String page);

    List<Acg12VideoDto> getBilibiliSearchDangumi(String key, String page);

    String getBilibiliPlayInfo(String av);


}
