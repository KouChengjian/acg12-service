package com.acg12.service.impl;

import com.acg12.dao.Acg12CollectPaletteDao;
import com.acg12.entity.po.Acg12CollectPaletteEntity;
import com.acg12.service.Acg12CollectPaletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * SERVICE - Acg12CollectPaletteEntity(CollectPalette)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12CollectPaletteServiceImpl extends GenericServiceImpl<Acg12CollectPaletteEntity, Long> implements Acg12CollectPaletteService {
	
	@Autowired
	private Acg12CollectPaletteDao acg12CollectPaletteDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12CollectPaletteDao);
	}

	public List<Acg12CollectPaletteEntity> findListByPage(Object parameter){
		return acg12CollectPaletteDao.findListByPage(parameter);
	}
	
	public List<Acg12CollectPaletteEntity> findListNewByPage(Object parameter) {
		return acg12CollectPaletteDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12CollectPaletteDao.deletes( parameter);
	}
	
	
}
