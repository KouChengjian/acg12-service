package com.acg12.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/12/28 20:06
 * Description:
 */
@Data
public class Acg12CaricatureChaptersDto {

    private int comicId;
    private String title;
    private int index ;// 第几集
    private List<Acg12CaricatureChaptersPageDto> pags = new ArrayList<>();
}
