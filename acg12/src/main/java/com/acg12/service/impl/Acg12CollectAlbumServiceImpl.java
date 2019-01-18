package com.acg12.service.impl;

import com.acg12.dao.Acg12CollectAlbumDao;
import com.acg12.entity.po.Acg12CollectAlbumEntity;
import com.acg12.service.Acg12CollectAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * SERVICE - Acg12CollectAlbumEntity(CollectAlbum)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12CollectAlbumServiceImpl extends GenericServiceImpl<Acg12CollectAlbumEntity, Long> implements Acg12CollectAlbumService {
	
	@Autowired
	private Acg12CollectAlbumDao acg12CollectAlbumDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12CollectAlbumDao);
	}

	public List<Acg12CollectAlbumEntity> findListByPage(Object parameter){
		return acg12CollectAlbumDao.findListByPage(parameter);
	}
	
	public List<Acg12CollectAlbumEntity> findListNewByPage(Object parameter) {
		return acg12CollectAlbumDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12CollectAlbumDao.deletes( parameter);
	}
	
	
}
