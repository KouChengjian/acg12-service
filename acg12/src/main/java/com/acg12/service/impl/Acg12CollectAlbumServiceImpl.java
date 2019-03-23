package com.acg12.service.impl;

import com.acg12.dao.Acg12CollectAlbumDao;
import com.acg12.entity.dto.Acg12AlbumDto;
import com.acg12.entity.po.Acg12CollectAlbumEntity;
import com.acg12.entity.po.Acg12CollectCaricatureEntity;
import com.acg12.service.Acg12CollectAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	@Override
	public List<Acg12AlbumDto> buildHasCollectAlbum(List<Acg12AlbumDto> acg12AlbumDtoList, long userId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("userId", userId);
		parameter.put("pageNumber", 0);
		parameter.put("pageSize", 1000);
		parameter.put("order", " id desc");
		List<Acg12CollectAlbumEntity> collectCaricatureList = findListByPage(parameter);
		Map<String, Acg12CollectAlbumEntity> collectSubjectMap = collectCaricatureList.stream().collect(Collectors.toMap(Acg12CollectAlbumEntity::getPinId, a -> a, (k1, k2) -> k1));
		acg12AlbumDtoList = acg12AlbumDtoList.stream().map(e -> {
			Acg12CollectAlbumEntity acg12CollectAlbumEntity = collectSubjectMap.get(e.getPinId());
			if (acg12CollectAlbumEntity != null) {
				e.setIsCollect(1);
			} else {
				e.setIsCollect(0);
			}
			return e;
		}).collect(Collectors.toList());
		return acg12AlbumDtoList;
	}


}
