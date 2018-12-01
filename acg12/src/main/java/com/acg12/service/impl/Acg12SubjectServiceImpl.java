package com.acg12.service.impl;

import com.acg12.dao.*;
import com.acg12.entity.dto.Acg12SubjectDto;
import com.acg12.entity.po.*;
import com.acg12.service.Acg12SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SERVICE - Acg12SubjectEntity(subject)
 *
 * @author wmj
 * @version 2.0
 */
@Service("acg12SubjectServiceImpl")
public class Acg12SubjectServiceImpl extends GenericServiceImpl<Acg12SubjectEntity, Long> implements Acg12SubjectService {

    @Autowired
    private Acg12SubjectDao acg12SubjectEntityDao;
    @Autowired
    private Acg12SubjectDetailDao acg12SubjectDetailDao;
    @Autowired
    private Acg12SubjectStaffDao acg12SubjectStaffDao;
    @Autowired
    private Acg12SubjectCrtDao acg12SubjectCrtDao;
    @Autowired
    private Acg12SubjectSongDao acg12SubjectSongDao;
    @Autowired
    private Acg12SubjectOffprintDao acg12SubjectOffprintDao;


    @Autowired
    public void setGenericDao() {
        super.setGenericDao(acg12SubjectEntityDao);
    }

    public List<Acg12SubjectEntity> findListByPage(Object parameter) {
        return acg12SubjectEntityDao.findListByPage(parameter);
    }

    public List<Acg12SubjectEntity> findListNewByPage(Object parameter) {
        return acg12SubjectEntityDao.findListNewByPage(parameter);
    }

    public Long deletes(Object parameter) {
        return acg12SubjectEntityDao.deletes(parameter);
    }

    @Override
    public Acg12SubjectDto findSubjectDto(long sId) {
        Acg12SubjectEntity acg12SubjectEntity = find("sId", sId);
        if (acg12SubjectEntity == null) {
            return null;
        }
        Map map = new HashMap();
        map.put("subjectId", acg12SubjectEntity.getId());
        List<Acg12SubjectDetailEntity> subjectDetailEntityList = acg12SubjectDetailDao.findByParams(map);
        List<Acg12SubjectStaffEntity> subjectStaffEntityList = acg12SubjectStaffDao.findByParams(map);
        List<Acg12SubjectCrtEntity> subjectCrtEntityList = acg12SubjectCrtDao.findByParams(map);
        List<Acg12SubjectOffprintEntity> subjectOffprintEntityList = null;
        List<Acg12SubjectSongEntity> subjectSongEntityList = null;
        if (acg12SubjectEntity.getType() == 1) {
            subjectOffprintEntityList = acg12SubjectOffprintDao.findByParams(map);
        } else if (acg12SubjectEntity.getType() == 3) {
            subjectSongEntityList = acg12SubjectSongDao.findByParams(map);
        }
        Acg12SubjectDto subjectDto = new Acg12SubjectDto();
        subjectDto.copy(acg12SubjectEntity);
        subjectDto.setDetailList(subjectDetailEntityList);
        subjectDto.setStaffList(subjectStaffEntityList);
        subjectDto.setCrtList(subjectCrtEntityList);
        subjectDto.setSongList(subjectSongEntityList);
        subjectDto.setOffprintList(subjectOffprintEntityList);
        return subjectDto;
    }

    @Override
    public Long savaSubjectDto(Acg12SubjectDto subjectDto) {
        Acg12SubjectEntity acg12SubjectEntity = find("sId", subjectDto.getSId());
        if (acg12SubjectEntity == null) {
            acg12SubjectEntity = subjectDto.getInfo();
            long code = acg12SubjectEntityDao.insert(acg12SubjectEntity);
            List<Acg12SubjectDetailEntity> subjectDetailEntityList = subjectDto.getDetailList();
            for (Acg12SubjectDetailEntity detailEntity : subjectDetailEntityList) {
                detailEntity.setSubjectId(acg12SubjectEntity.getId());
                acg12SubjectDetailDao.insert(detailEntity);
            }
            List<Acg12SubjectStaffEntity> subjectStaffEntityList = subjectDto.getStaffList();
            for (Acg12SubjectStaffEntity staffEntity : subjectStaffEntityList) {
                staffEntity.setSubjectId(acg12SubjectEntity.getId());
                acg12SubjectStaffDao.insert(staffEntity);
            }
            List<Acg12SubjectCrtEntity> subjectCrtEntityList = subjectDto.getCrtList();
            for (Acg12SubjectCrtEntity crtEntity : subjectCrtEntityList) {
                crtEntity.setSubjectId(acg12SubjectEntity.getId());
                acg12SubjectCrtDao.insert(crtEntity);
            }
            List<Acg12SubjectOffprintEntity> subjectOffprintEntityList = subjectDto.getOffprintList();
            for (Acg12SubjectOffprintEntity offprintEntity : subjectOffprintEntityList) {
                offprintEntity.setSubjectId(acg12SubjectEntity.getId());
                acg12SubjectOffprintDao.insert(offprintEntity);
            }
            List<Acg12SubjectSongEntity> subjectSongEntityList = subjectDto.getSongList();
            for (Acg12SubjectSongEntity songEntity : subjectSongEntityList) {
                songEntity.setSubjectId(acg12SubjectEntity.getId());
                acg12SubjectSongDao.insert(songEntity);
            }
            return code;
        }

        // TODO:  查看是否有更新 目前就更新subject 与其光联的先不更新了
        Acg12SubjectEntity curSubjectEntity = subjectDto.getInfo();
        if (!acg12SubjectEntity.equals(curSubjectEntity)) {
            curSubjectEntity.setId(acg12SubjectEntity.getId());
            curSubjectEntity.setCreateTime(acg12SubjectEntity.getCreateTime());
            return acg12SubjectEntityDao.update(curSubjectEntity);
        }

        return null;
    }

    @Override
    public Long deleteSubjectDto(Acg12SubjectDto subjectDto) {
        return null;
    }
}
