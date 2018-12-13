package com.acg12.service.impl;

import com.acg12.dao.Acg12PersonDao;
import com.acg12.dao.Acg12PersonDetailDao;
import com.acg12.entity.dto.Acg12PersonDto;
import com.acg12.entity.po.*;
import com.acg12.service.Acg12PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SERVICE - Acg12Person(person)
 *
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12PersonServiceImpl extends GenericServiceImpl<Acg12PersonEntity, Long> implements Acg12PersonService {

    @Autowired
    private Acg12PersonDao acg12PersonDao;
    @Autowired
    private Acg12PersonDetailDao acg12PersonDetailDao;

    @Autowired
    public void setGenericDao() {
        super.setGenericDao(acg12PersonDao);
    }

    public List<Acg12PersonEntity> findListByPage(Object parameter) {
        return acg12PersonDao.findListByPage(parameter);
    }

    public List<Acg12PersonEntity> findListNewByPage(Object parameter) {
        return acg12PersonDao.findListNewByPage(parameter);
    }

    public Long deletes(Object parameter) {
        return acg12PersonDao.deletes(parameter);
    }

    @Override
    public Acg12PersonDto findPersonDto(long pId) {
        Acg12PersonEntity acg12PersonEntity = find("pId", pId);
        if (acg12PersonEntity == null) {
            return null;
        }
        Map map = new HashMap();
        map.put("personId", acg12PersonEntity.getId());
        List<Acg12PersonDetailEntity> subjectDetailEntityList = acg12PersonDetailDao.findByParams(map);

        Acg12PersonDto acg12PersonDto = new Acg12PersonDto();
        acg12PersonDto.copy(acg12PersonEntity);
        acg12PersonDto.setDetailList(subjectDetailEntityList);
        return acg12PersonDto;
    }

    @Override
    public Long savaPersonDto(Acg12PersonDto personDto) {
        Acg12PersonEntity acg12PersonEntity = find("pId", personDto.getPId());
        if (acg12PersonEntity == null) {
            acg12PersonEntity = personDto.chanage();
            long code = acg12PersonDao.insert(acg12PersonEntity);
            System.out.println("code = " + code);
            List<Acg12PersonDetailEntity> personDetailEntityList = personDto.getDetailList();
            for (Acg12PersonDetailEntity detailEntity : personDetailEntityList) {
                detailEntity.setPersonId(acg12PersonEntity.getId());
                acg12PersonDetailDao.insert(detailEntity);
            }
            return code;
        }

        // TODO:  查看是否有更新 目前就更新subject 与其光联的先不更新了
        Acg12PersonEntity curPersonEntity = personDto.chanage();
        if (!acg12PersonEntity.equals(curPersonEntity)) {
            curPersonEntity.setId(acg12PersonEntity.getId());
            curPersonEntity.setCreateTime(acg12PersonEntity.getCreateTime());
            return acg12PersonDao.update(curPersonEntity);
        }

        return null;
    }

    @Override
    public Long deletePersonDto(Acg12PersonDto subjectDto) {
        return null;
    }


}
