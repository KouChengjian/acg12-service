package com.acg12.dao;

import com.acg12.entity.po.Acg12CollectAlbumEntity;
import com.framework.loippi.mybatis.dao.GenericDao;

import java.util.List;

/**
 * DAO - Acg12CollectAlbumEntity(CollectAlbum)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12CollectAlbumDao  extends GenericDao<Acg12CollectAlbumEntity, Long> {
	List<Acg12CollectAlbumEntity> findListByPage(Object parameter);
	List<Acg12CollectAlbumEntity> findListNewByPage(Object parameter);
    Long deletes(Object parameter);
}
