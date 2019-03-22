package com.acg12.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/12/28 18:10
 * Description: 漫画
 */
@Data
public class Acg12CaricatureDto {

    private int comicId;
    private int type; // 1:酷克
    private String cover;
    private String title;
    private Integer isCollect; /** 是否收藏 */
    private List<Acg12CaricatureChaptersDto> chaptersList = new ArrayList<>();
}
