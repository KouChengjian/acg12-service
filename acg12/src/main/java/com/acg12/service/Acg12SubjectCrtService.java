package com.acg12.service;

import com.acg12.entity.po.Acg12SubjectCrtEntity;

import java.util.List;

/**
 * SERVICE - Acg12SubjectCrt(subjectCrt)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12SubjectCrtService extends GenericService<Acg12SubjectCrtEntity, Long> {
    public List<Acg12SubjectCrtEntity> findListByPage(Object parameter);

    public List<Acg12SubjectCrtEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);
}
