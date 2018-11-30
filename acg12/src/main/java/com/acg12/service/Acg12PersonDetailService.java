package com.acg12.service;

import com.acg12.entity.po.Acg12PersonDetailEntity;

import java.util.List;

/**
 * SERVICE - Acg12PersonDetail(personDetail)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12PersonDetailService extends GenericService<Acg12PersonDetailEntity, Long> {
    public List<Acg12PersonDetailEntity> findListByPage(Object parameter);

    public List<Acg12PersonDetailEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);
}
