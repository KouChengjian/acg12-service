package com.acg12.service;

import com.acg12.entity.po.Acg12SubjectDetailEntity;

import java.util.List;

/**
 * SERVICE - Acg12SubjectDetail(subjectDetail)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12SubjectDetailService extends GenericService<Acg12SubjectDetailEntity, Long> {
    public List<Acg12SubjectDetailEntity> findListByPage(Object parameter);

    public List<Acg12SubjectDetailEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);
}
