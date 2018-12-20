package com.acg12.service.impl;

import com.acg12.entity.dto.Acg12AlbumDto;
import com.acg12.entity.dto.Acg12PaletteDto;
import com.acg12.service.Acg12ResourceService;
import com.acg12.utils.res.BgmResourceUtil;
import com.acg12.utils.res.DongManZhiJiaResourceUtil;
import com.acg12.utils.res.HuaBanResourceUtil;
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
    public String getBgmCalendarList() {
        return BgmResourceUtil.getCalendarList();
    }

}
