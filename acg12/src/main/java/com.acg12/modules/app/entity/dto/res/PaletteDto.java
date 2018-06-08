package com.acg12.modules.app.entity.dto.res;


import com.acg12.modules.app.entity.po.Palette;

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
