package com.acg12.dao;

import com.acg12.entity.po.Acg12SubjectStaffEntity;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.util.List;

/**
 * DAO - Acg12SubjectStaff(subjectStaff)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12SubjectStaffDao extends GenericDao<Acg12SubjectStaffEntity, Long> {
    List<Acg12SubjectStaffEntity> findListByPage(Object parameter);

    List<Acg12SubjectStaffEntity> findListNewByPage(Object parameter);

    Long deletes(Object parameter);
}
