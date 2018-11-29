package com.acg12.entity.dto;

import com.acg12.entity.po.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/11/28 17:09
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

    private Acg12SubjectEntity info;

    private List<Acg12SubjectDetailEntity> detailList = new ArrayList<>();
    private List<Acg12SubjectStaffEntity> staffList = new ArrayList<>();
    private List<Acg12SubjectCrtEntity> crtList = new ArrayList<>();
    private List<Acg12SubjectOffprintEntity> offprintList = new ArrayList<>();
    private List<Acg12SubjectSongEntity> songList = new ArrayList<>();

}
