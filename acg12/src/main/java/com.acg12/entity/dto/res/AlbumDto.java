package com.acg12.entity.dto.res;

import com.acg12.entity.po.Album;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18.
 */
public class AlbumDto {

    private List<Album> list;

    public AlbumDto(List<Album> list){
        this.list = list;
    }

    public List<Album> getList() {
        return list;
    }

    public void setList(List<Album> list) {
        this.list = list;
    }
}
