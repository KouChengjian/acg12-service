package com.acg12.entity.dto;

import com.acg12.entity.po.Acg12TagEntity;
import lombok.Data;

import java.util.List;

@Data
public class Acg12IndexDto {

    /** id */
    private Long id;

    /** 标题 */
    private String title;

    /** 类型 */
    private Integer type;

    /** 类型名 */
    private String typeName;

    /** 锁定 */
    private Integer isLock;

    /** 图片 */
    private String cover;

    /** 排序 */
    private Integer sort;

    private List<Acg12TagEntity> tagList;

    private List<Acg12CaricatureTagDto> tags;

}
