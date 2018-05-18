package com.acg12.entity.dto.res;

import com.acg12.entity.po.Palette;
import com.acg12.utils.pagination.PageInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18.
 */
public class PaletteDto {

    private List<Palette> list;

    public PaletteDto(List<Palette> list){
        this.list = list;
    }

    public List<Palette> getList() {
        return list;
    }

    public void setList(List<Palette> list) {
        this.list = list;
    }
}
