package com.acg12.service;

import com.acg12.entity.dto.SubjectDto;
import com.acg12.entity.po.Acg12SubjectEntity;

import java.util.List;

/**
 * SERVICE - Acg12SubjectEntity(subject)
 *
 * @author wmj
 * @version 2.0
 */
public interface Acg12SubjectService extends GenericService<Acg12SubjectEntity, Long> {

    public List<Acg12SubjectEntity> findListByPage(Object parameter);

    public List<Acg12SubjectEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);

    public SubjectDto findSubjectDto(long sId);

    public Long savaSubjectDto(SubjectDto subjectDto);

    public Long deleteSubjectDto(SubjectDto subjectDto);
}
