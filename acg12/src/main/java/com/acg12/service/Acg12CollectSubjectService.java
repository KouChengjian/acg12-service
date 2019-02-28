package com.acg12.service;

import com.acg12.entity.dto.Acg12SubjectDto;
import com.acg12.entity.po.Acg12CollectSubjectEntity;

import java.util.List;

/**
 * SERVICE - Acg12CollectSubjectEntity(collectSubject)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12CollectSubjectService extends GenericService<Acg12CollectSubjectEntity, Long> {

    public List<Acg12CollectSubjectEntity> findListByPage(Object parameter);

    public List<Acg12CollectSubjectEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);

    public Acg12SubjectDto buildHasCollectToSubject(Acg12SubjectDto acg12SubjectDto , long userId);
}
