package com.acg12.service.impl;

import com.acg12.dao.Acg12CharacterDetailDao;
import com.acg12.dao.Acg12PersonDetailDao;
import com.acg12.entity.dto.Acg12CharacterDto;
import com.acg12.entity.dto.Acg12PersonDto;
import com.acg12.entity.po.Acg12CharacterDetailEntity;
import com.acg12.entity.po.Acg12CharacterEntity;
import com.acg12.entity.po.Acg12PersonDetailEntity;
import com.acg12.entity.po.Acg12PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg12.dao.Acg12CharacterDao;
import com.acg12.service.Acg12CharacterService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Acg12CharacterDetailDao acg12CharacterDetailDao;
	
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

	@Override
	public Acg12CharacterDto findCharacterDto(long cId) {
		Acg12CharacterEntity acg12CharacterEntity = find("cId", cId);
		if (acg12CharacterEntity == null) {
			return null;
		}
		Map map = new HashMap();
		map.put("characterId", acg12CharacterEntity.getId());
		List<Acg12CharacterDetailEntity> subjectDetailEntityList = acg12CharacterDetailDao.findByParams(map);

		Acg12CharacterDto acg12CharacterDto = new Acg12CharacterDto();
		acg12CharacterDto.copy(acg12CharacterEntity);
		acg12CharacterDto.setDetailList(subjectDetailEntityList);
		return acg12CharacterDto;
	}

	@Override
	public Long savaCharacterDto(Acg12CharacterDto characterDto) {
		Acg12CharacterEntity acg12CharacterEntity = find("cId", characterDto.getCId());
		if (acg12CharacterEntity == null) {
			acg12CharacterEntity = characterDto.chanage();
			long code = acg12CharacterDao.insert(acg12CharacterEntity);

			List<Acg12CharacterDetailEntity> characterDtoDetailList = characterDto.getDetailList();
			for (Acg12CharacterDetailEntity detailEntity : characterDtoDetailList) {
				detailEntity.setCharacterId(acg12CharacterEntity.getId());
				acg12CharacterDetailDao.insert(detailEntity);
			}
			return code;
		}

		// TODO:  查看是否有更新 目前就更新subject 与其光联的先不更新了
		Acg12CharacterEntity curCharacterEntity = characterDto.chanage();
		if (!acg12CharacterEntity.equals(curCharacterEntity)) {
			curCharacterEntity.setId(acg12CharacterEntity.getId());
			curCharacterEntity.setCreateTime(acg12CharacterEntity.getCreateTime());
			return acg12CharacterDao.update(curCharacterEntity);
		}
		return null;
	}

	@Override
	public Long deleteCharacterDto(Acg12CharacterDto characterDto) {
		return null;
	}


}
