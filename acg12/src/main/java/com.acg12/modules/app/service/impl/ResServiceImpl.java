package com.acg12.modules.app.service.impl;

import com.acg12.common.utils.crawler.BgmCrawler;
import com.acg12.common.utils.crawler.BiliBiliCrawler;
import com.acg12.common.utils.crawler.MoeGirlCrawler;
import com.acg12.common.utils.StringUtil;
import com.acg12.common.utils.crawler.HuaBanCrawler;
import com.acg12.modules.app.dao.subject.SubjectDao;
import com.acg12.modules.app.entity.dto.IndexDto;
import com.acg12.modules.app.entity.dto.Video;
import com.acg12.modules.app.entity.po.Album;
import com.acg12.modules.app.entity.po.Palette;
import com.acg12.modules.app.entity.po.Tag;
import com.acg12.modules.app.entity.po.subject.SubjectEntity;
import com.acg12.modules.app.service.ResService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 资源里面都是抓取的数据
 * Created by kouchengjian on 2017/3/9.
 */
@Service
public class ResServiceImpl implements ResService {

    @Resource
    SubjectDao mSubjectDao;

    /**
     * --------------------------------------自定义资源-------------------------------------------
     */
    @Cacheable(value = "resource_cache", key = "'homeContent'")
    @Override
    public IndexDto getIndex() {
        IndexDto indexDto = new IndexDto();
        indexDto.setUrl("http://acg12.club/res/images/weixin20180125141034.png");
        List<Tag> tagList = new ArrayList<>();
        Tag tag1 = new Tag();
        tag1.setTag("社畜");
        tag1.setImage("http://acg12.club/res/images/a9712e66adddc2a5c0aa8.png");
        tagList.add(tag1);

        tag1 = new Tag();
        tag1.setTag("铁轨");
        tag1.setImage("http://acg12.club/res/images/18012514102.png");
        tagList.add(tag1);

        tag1 = new Tag();
        tag1.setTag("夏娜");
        tag1.setImage("http://acg12.club/res/images/7c1ed21b0ef41bd552c80d.png");
        tagList.add(tag1);

        tag1 = new Tag();
        tag1.setTag("萌萌哒");
        tag1.setImage("http://acg12.club/res/images/a5d253eb0881bd418d88.png");
        tagList.add(tag1);

        indexDto.setTagList(tagList);
        return indexDto;
    }

    /**
     * --------------------------------------花瓣网资源-------------------------------------------
     */
    @Cacheable(value = "resource_cache", key = "'albumList_max=' + #max")
    @Override
    public List<Album> getHuaBanImages(String max) {
        return HuaBanCrawler.getAlbumList(max);
    }

    @Cacheable(value = "resource_cache", key = "'paletteList_max=' + #max")
    @Override
    public List<Palette> getHuaBanBoards(String max) {
        return HuaBanCrawler.getPaletteList(max);
    }

    @Cacheable(value = "resource_cache", key = "'paletteAlbumList_boardId=' + #boardId +',max=' + #max")
    @Override
    public List<Album> getHuaBanBoardsToImages(String boardId, String max) {
        return HuaBanCrawler.getBoardsToAlbumList(boardId, max);
    }

    @Cacheable(value = "resource_cache", key = "'searchAlbum_key=' + #key + ',Page=' + #page")
    @Override
    public List<Album> getHuaBanSearchImages(String key, String page) {
        return HuaBanCrawler.getSearchAlbum(key, page);
    }

    @Cacheable(value = "resource_cache", key = "'searchBoards_key=' + #key + ',Page=' + #page")
    @Override
    public List<Palette> getHuaBanSearchBoards(String key, String page) {
        return HuaBanCrawler.getSearchPalette(key, page);
    }

    /**
     * --------------------------------------动漫之家资源--------------------------------------------
     */

    @Override
    public JSONArray getNews(String pager) {
        return null;
    }

    /**
     * --------------------------------------萌娘百科资源--------------------------------------------
     */
//    @Cacheable(value = "resource_cache", key = "'dangumiList_page=' + #page")
    @Override
    public JSONArray getMoeGirlSearchKeyList(String key) {
        return MoeGirlCrawler.getMoeGirlSearchKeyList(key);
    }

    @Override
    public JSONArray gettMoeGirlSearchKeyInfo(String key) {
        MoeGirlCrawler.gettMoeGirlSearchKeyInfo(key);
        return null;
    }

    /**
     * --------------------------------------番组计划资源--------------------------------------------
     */
    @Override
    public JSONObject getBgmSearchKeyList(String key) {
        return BgmCrawler.getBgmSearchSubjectList(key, 0, 0);
    }

    /**
     *
     * @param sId
     * @param type 0:subject 1:preson 2:cre
     * @param key
     * @return
     */
    @Override
    public JSONObject getBgmSubjectInfo(int sId, int type, String key) {
//        SubjectEntity subjectEntity = mSubjectDao.queryBySId(sId);
//        if(type == 1){
//            return BgmCrawler.getPersonInfo(sId)
//        }
        return null;
    }


    /**
     * --------------------------------------bilibili资源--------------------------------------------
     */
    @Cacheable(value = "resource_cache", key = "'videoTypeList_type=' + #type + ',page=' + #page")
    @Override
    public JSONObject getVideoTypeList(String type, String page) {
        String url = BiliBiliCrawler.getMoreVideoUrl(type);
        List<Video> videoList = BiliBiliCrawler.getVideoTypeList(url, page);
        try {
            Gson gson = new Gson();
            JSONArray paletteJson = new JSONArray(gson.toJson(videoList));
            JSONObject array = new JSONObject();
            array.put("video", paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getVideoTypeList()"
                    + e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache", key = "'videoTypeInfo_av=' + #av")
    @Override
    public JSONObject getVideoTypeInfo(String av) {
        Video video = BiliBiliCrawler.getVideoTypeInfo(av);
        try {
            Gson gson = new Gson();
            JSONObject paletteJson = new JSONObject(gson.toJson(video));
            JSONObject array = new JSONObject();
            array.put("video", paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getVideoTypeInfo()"
                    + e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache", key = "'dangumiList_page=' + #page")
    @Override
    public JSONObject getDangumiList(String page) {
        List<Video> videoList = BiliBiliCrawler.getDangumiList(page);
        try {
            Gson gson = new Gson();
            JSONArray paletteJson = new JSONArray(gson.toJson(videoList));
            JSONObject array = new JSONObject();
            array.put("video", paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getDangumiList()"
                    + e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache", key = "'dangumiInfo_bmId=' + #bmId")
    @Override
    public JSONObject getDangumiInfo(String bmId) {
        Video video = BiliBiliCrawler.getDangumiInfo2(bmId);
        try {
            Gson gson = new Gson();
            JSONObject paletteJson = new JSONObject(gson.toJson(video));
            JSONObject array = new JSONObject();
            array.put("video", paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getDangumiInfo()"
                    + e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache", key = "'dangumiAV_id=' + #id")
    @Override
    public JSONObject getDangumiAV(String id) {
        String av = BiliBiliCrawler.getDangumiAV(id);
        try {
            JSONObject array = new JSONObject();
            array.put("av", av);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getDangumiAV()"
                    + e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache", key = "'searchVideo_key=' + #key + ',Page=' + #page")
    @Override
    public JSONObject getSearchVideo(String key, String page) {
        List<Video> paletteList = BiliBiliCrawler.getSearchVideo(key, page);
        try {
            Gson gson = new Gson();
            JSONArray paletteJson = new JSONArray(gson.toJson(paletteList));
            JSONObject array = new JSONObject();
            array.put("video", paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getSearchVideo()"
                    + e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache", key = "'searchDangumi_key=' + #key + ',Page=' + #page")
    @Override
    public JSONObject getSearchDangumi(String key, String page) {
        List<Video> paletteList = BiliBiliCrawler.getSearchBangunmi(key, page);
        try {
            Gson gson = new Gson();
            JSONArray paletteJson = new JSONArray(gson.toJson(paletteList));
            JSONObject array = new JSONObject();
            array.put("video", paletteJson);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getSearchDangumi()"
                    + e.toString());
        }
        return null;
    }

    @Cacheable(value = "resource_cache", key = "'playInfo_av=' + #av")
    @Override
    public JSONObject getPlayInfo(String av) {
        JSONObject content = BiliBiliCrawler.getPlayUrl(av);
        try {
            JSONObject array = new JSONObject();
            array.put("info", content);
            return array;
        } catch (Exception e) {
            System.err.println("ResServiceImpl->getPlayInfo()"
                    + e.toString());
        }
        return null;
    }
}
