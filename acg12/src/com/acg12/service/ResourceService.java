package com.acg12.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by kouchengjian on 2017/3/9.
 */
@Service
public interface ResourceService {

    public JSONObject getIndex();

    public JSONObject getAlbumList(String max);

    public JSONObject getBoardsList(String max);


}
