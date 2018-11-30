package com.acg12.service;

import com.acg12.entity.po.Acg12SubjectStaffEntity;

import java.util.List;

/**
 * SERVICE - Acg12SubjectStaff(subjectStaff)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12SubjectStaffService extends GenericService<Acg12SubjectStaffEntity, Long> {
    public List<Acg12SubjectStaffEntity> findListByPage(Object parameter);

    public List<Acg12SubjectStaffEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);
}
