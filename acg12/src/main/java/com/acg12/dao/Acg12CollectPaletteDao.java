package com.acg12.dao;

import com.acg12.entity.po.Acg12CollectPaletteEntity;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.util.List;

/**
 * DAO - Acg12CollectPaletteEntity(CollectPalette)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12CollectPaletteDao  extends GenericDao<Acg12CollectPaletteEntity, Long> {
	List<Acg12CollectPaletteEntity> findListByPage(Object parameter);
	List<Acg12CollectPaletteEntity> findListNewByPage(Object parameter);
    Long deletes(Object parameter);
}
