package com.acg12.service.impl;

import com.acg12.entity.po.Acg12CharacterDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg12.dao.Acg12CharacterDetailDao;
import com.acg12.service.Acg12CharacterDetailService;
import java.util.List;
/**
 * SERVICE - Acg12CharacterDetail(characterDetail)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12CharacterDetailServiceImpl extends GenericServiceImpl<Acg12CharacterDetailEntity, Long> implements Acg12CharacterDetailService {
	
	@Autowired
	private Acg12CharacterDetailDao acg12CharacterDetailDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12CharacterDetailDao);
	}

	public List<Acg12CharacterDetailEntity> findListByPage(Object parameter){
		return acg12CharacterDetailDao.findListByPage(parameter);
	}
	
	public List<Acg12CharacterDetailEntity> findListNewByPage(Object parameter) {
		return acg12CharacterDetailDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12CharacterDetailDao.deletes( parameter);
	}
	
	
}
