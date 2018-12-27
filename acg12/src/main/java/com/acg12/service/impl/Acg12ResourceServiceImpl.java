package com.acg12.service.impl;

import com.acg12.entity.dto.*;
import com.acg12.entity.po.Acg12CharacterEntity;
import com.acg12.service.Acg12ResourceService;
import com.acg12.utils.res.*;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/12/20 10:32
 * Description:
 */
@Service
public class Acg12ResourceServiceImpl implements Acg12ResourceService {

    @Override
    public List<Acg12AlbumDto> getHuaBanImages(String max) {
        return HuaBanResourceUtil.getAlbumList(max);
    }

    @Override
    public List<Acg12PaletteDto> getHuaBanBoards(String max) {
        return HuaBanResourceUtil.getPaletteList(max);
    }

    @Override
    public List<Acg12AlbumDto> getHuaBanBoardsToImages(String boardId, String max) {
        return HuaBanResourceUtil.getBoardsToAlbumList(boardId, max);
    }

    @Override
    public List<Acg12AlbumDto> getHuaBanSearchImages(String key, String page) {
        return HuaBanResourceUtil.getSearchAlbum(key, page);
    }

    @Override
    public List<Acg12PaletteDto> getHuaBanSearchBoards(String key, String page) {
        return HuaBanResourceUtil.getSearchPalette(key, page);
    }

    @Override
    public String getDongManZhiJiaNews(String pager) {
        return DongManZhiJiaResourceUtil.getNewList(pager).toString();
    }

    @Override
    public String getMengNiangSearchKeyList(String key) {
        return MengNiangResourceUtil.getMoeGirlSearchKeyList(key);
    }

    @Override
    public String gettMoeGirlSearchKeyInfo(String key) {
        return MengNiangResourceUtil.gettMoeGirlSearchKeyInfo(key);
    }

    @Override
    public String getBgmSearchKeyList(String key) {
        JSONArray jsonArray = BgmResourceUtil.getBgmSearchPresonList(key);
        return BgmResourceUtil.getBgmSearchSubjectList(key, 0, 0, jsonArray);
    }

    @Override
    public String getBgmCalendarList() {
        return BgmResourceUtil.getCalendarList();
    }

    @Override
    public Acg12SubjectDto getBgmSubject(int sId) {
        Acg12SubjectDto acg12SubjectDto = BgmResourceUtil.getSubjectDto(sId);
        return acg12SubjectDto;
    }

    @Override
    public Acg12PersonDto getBgmPerson(int pId) {
        Acg12PersonDto acg12PersonDto = BgmResourceUtil.getPersonDto(pId);
        return acg12PersonDto;
    }

    @Override
    public Acg12CharacterDto getBgmCharacter(int cId) {
        Acg12CharacterDto acg12CharacterDto = BgmResourceUtil.getCharacterDto(cId);
        return acg12CharacterDto;
    }

    @Override
    public List<Acg12VideoDto> getBilibiliVideoTypeList(String type, String page) {
        String url = BiliBiliResourceUtil.getMoreVideoUrl(type);
        return BiliBiliResourceUtil.getVideoTypeList(url, page);
    }

    @Override
    public Acg12VideoDto getBilibiliVideoTypeInfo(String av) {
        return BiliBiliResourceUtil.getVideoTypeInfo(av);
    }

    @Override
    public List<Acg12VideoDto> getBilibiliDangumiList(String page) {
        return BiliBiliResourceUtil.getDangumiList(page);
    }

    @Override
    public Acg12VideoDto getBilibiliDangumiInfo(String bmId) {
        return BiliBiliResourceUtil.getDangumiInfo2(bmId);
    }

    @Override
    public String getBilibiliDangumiAV(String id) {
        return BiliBiliResourceUtil.getDangumiAV(id);
    }

    @Override
    public List<Acg12VideoDto> getBilibiliSearchVideo(String key, String page) {
        return BiliBiliResourceUtil.getSearchVideo(key, page);
    }

    @Override
    public List<Acg12VideoDto> getBilibiliSearchDangumi(String key, String page) {
        return BiliBiliResourceUtil.getSearchBangunmi(key, page);
    }

    @Override
    public String getBilibiliPlayInfo(String av) {
        return BiliBiliResourceUtil.getPlayUrl(av);
    }

}
