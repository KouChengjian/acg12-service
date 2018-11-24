package com.acg12.service.impl;

import com.acg12.entity.po.Acg12CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg12.dao.Acg12CharacterDao;
import com.acg12.service.Acg12CharacterService;
import java.util.List;
/**
 * SERVICE - Acg12Character(character)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12CharacterServiceImpl extends GenericServiceImpl<Acg12CharacterEntity, Long> implements Acg12CharacterService {
	
	@Autowired
	private Acg12CharacterDao acg12CharacterDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12CharacterDao);
	}

	public List<Acg12CharacterEntity> findListByPage(Object parameter){
		return acg12CharacterDao.findListByPage(parameter);
	}
	
	public List<Acg12CharacterEntity> findListNewByPage(Object parameter) {
		return acg12CharacterDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12CharacterDao.deletes( parameter);
	}
	
	
}
