package com.acg12.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2019/5/28 18:35
 * Description:
 */
@Data
public class Acg12CaricatureTagDto {
    private long id;
    private String name;
    private List<Acg12CaricatureDto> list = new ArrayList<>();

    public Acg12CaricatureTagDto(int id, String name ,List<Acg12CaricatureDto> list) {
        this.id = id;
        this.name = name;
        this.list = list;
    }

}
