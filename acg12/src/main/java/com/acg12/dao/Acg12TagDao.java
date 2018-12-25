package com.acg12.dao;

import com.acg12.entity.po.Acg12Tag;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - Acg12Tag(标签)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12TagDao  extends GenericDao<Acg12Tag, Long> {
	List<Acg12Tag> findListByPage(Object parameter);
	List<Acg12Tag> findListNewByPage(Object parameter);
    Long deletes(Object parameter);
}
