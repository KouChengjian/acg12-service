package com.acg12.service.impl;

import com.acg12.dao.Acg12CollectSubjectDao;
import com.acg12.entity.dto.Acg12SubjectDto;
import com.acg12.entity.po.Acg12CollectSubjectEntity;
import com.acg12.service.Acg12CollectSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SERVICE - Acg12CollectSubjectEntity(collectSubject)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12CollectSubjectServiceImpl extends GenericServiceImpl<Acg12CollectSubjectEntity, Long> implements Acg12CollectSubjectService {
	
	@Autowired
	private Acg12CollectSubjectDao acg12CollectSubjectDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12CollectSubjectDao);
	}

	public List<Acg12CollectSubjectEntity> findListByPage(Object parameter){
		return acg12CollectSubjectDao.findListByPage(parameter);
	}
	
	public List<Acg12CollectSubjectEntity> findListNewByPage(Object parameter) {
		return acg12CollectSubjectDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12CollectSubjectDao.deletes( parameter);
	}

	@Override
	public Acg12SubjectDto buildHasCollectToSubject(Acg12SubjectDto acg12SubjectDto, long userId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userId", userId);
		parameter.put("pageNumber", 0);
		parameter.put("pageSize", 1000);
		parameter.put("order", " id desc");
		List<Acg12CollectSubjectEntity> collectSubjectList = findListByPage(parameter);
		Map<Integer, Acg12CollectSubjectEntity> collectSubjectMap = collectSubjectList.stream().collect(Collectors.toMap(Acg12CollectSubjectEntity::getRelevanceId, a -> a,(k1, k2)->k1));
		Acg12CollectSubjectEntity acg12CollectSubjectEntity = collectSubjectMap.get(acg12SubjectDto.getSId());
		if(acg12CollectSubjectEntity != null){
			acg12SubjectDto.setIsCollect(1);
		} else {
			acg12SubjectDto.setIsCollect(0);
		}
		return acg12SubjectDto;
	}


}
