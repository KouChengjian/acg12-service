package com.acg12.service;

import com.acg12.entity.po.Acg12SubjectOffprintEntity;

import java.util.List;

/**
 * SERVICE - Acg12SubjectOffprint(subjectOffprint)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12SubjectOffprintService extends GenericService<Acg12SubjectOffprintEntity, Long> {
    public List<Acg12SubjectOffprintEntity> findListByPage(Object parameter);

    public List<Acg12SubjectOffprintEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);
}
