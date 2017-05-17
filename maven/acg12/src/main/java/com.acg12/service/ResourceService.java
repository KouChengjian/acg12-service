package com.acg12.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by kouchengjian on 2017/3/9.
 */
@Service
public interface ResourceService {

    JSONObject getIndex();

    JSONObject getAlbumList(String max);

    JSONObject getBoardsList(String max);

    JSONObject getBoardsToAlbumList(String boardId, String max);

    JSONObject getVideoTypeList(String type, String page);

    JSONObject getVideoTypeInfo(String av);

    JSONObject getDangumiList(String page);

    JSONObject getDangumiInfo(String av);

    JSONObject getDangumiAV(String id);

    JSONObject getSearchAlbum(String key, String page);

    JSONObject getSearchBoards(String key, String page);

    JSONObject getSearchVideo(String key, String page);

    JSONObject getSearchDangumi(String key, String page);

    JSONObject getPlayInfo(String av);


}
