package com.acg12.service.impl;

import com.acg12.dao.Acg12CollectCaricatureDao;
import com.acg12.entity.dto.Acg12CaricatureDto;
import com.acg12.entity.po.Acg12CollectCaricatureEntity;
import com.acg12.entity.po.Acg12CollectSubjectEntity;
import com.acg12.service.Acg12CollectCaricatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SERVICE - Acg12CollectCaricatureEntity(CollectCaricature)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12CollectCaricatureServiceImpl extends GenericServiceImpl<Acg12CollectCaricatureEntity, Long> implements Acg12CollectCaricatureService {
	
	@Autowired
	private Acg12CollectCaricatureDao acg12CollectCaricatureDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12CollectCaricatureDao);
	}

	public List<Acg12CollectCaricatureEntity> findListByPage(Object parameter){
		return acg12CollectCaricatureDao.findListByPage(parameter);
	}
	
	public List<Acg12CollectCaricatureEntity> findListNewByPage(Object parameter) {
		return acg12CollectCaricatureDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12CollectCaricatureDao.deletes( parameter);
	}

	@Override
	public List<Acg12CaricatureDto> buildHasCollectToCaricature(List<Acg12CaricatureDto> caricatureDtoList, long userId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("userId", userId);
		parameter.put("pageNumber", 0);
		parameter.put("pageSize", 1000);
		parameter.put("order", " id desc");
		List<Acg12CollectCaricatureEntity> collectCaricatureList = findListByPage(parameter);
		Map<Long, Acg12CollectCaricatureEntity> collectSubjectMap = collectCaricatureList.stream().collect(Collectors.toMap(Acg12CollectCaricatureEntity::getComicId, a -> a, (k1, k2) -> k1));
		caricatureDtoList = caricatureDtoList.stream().map(e -> {
			Acg12CollectCaricatureEntity acg12CollectSubjectEntity = collectSubjectMap.get(Long.valueOf(e.getComicId()).longValue());
			if (acg12CollectSubjectEntity != null) {
				e.setIsCollect(1);
			} else {
				e.setIsCollect(0);
			}
			return e;
		}).collect(Collectors.toList());
		return caricatureDtoList;
	}


}
