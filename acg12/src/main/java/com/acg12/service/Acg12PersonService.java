package com.acg12.service;

import com.acg12.entity.dto.Acg12PersonDto;
import com.acg12.entity.dto.Acg12SubjectDto;
import com.acg12.entity.po.Acg12PersonEntity;

import java.util.List;

/**
 * SERVICE - Acg12Person(person)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12PersonService extends GenericService<Acg12PersonEntity, Long> {
    public List<Acg12PersonEntity> findListByPage(Object parameter);

    public List<Acg12PersonEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);

    public Acg12PersonDto findPersonDto(long pId);

    public Long savaPersonDto(Acg12PersonDto personDto);

    public Long deletePersonDto(Acg12PersonDto personDto);
}
