package com.acg12.service.impl;

import com.acg12.entity.po.Acg12SubjectSongEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acg12.dao.Acg12SubjectSongDao;
import com.acg12.service.Acg12SubjectSongService;
import java.util.List;
/**
 * SERVICE - Acg12SubjectSong(subjectSong)
 * 
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12SubjectSongServiceImpl extends GenericServiceImpl<Acg12SubjectSongEntity, Long> implements Acg12SubjectSongService {
	
	@Autowired
	private Acg12SubjectSongDao acg12SubjectSongDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(acg12SubjectSongDao);
	}

	public List<Acg12SubjectSongEntity> findListByPage(Object parameter){
		return acg12SubjectSongDao.findListByPage(parameter);
	}
	
	public List<Acg12SubjectSongEntity> findListNewByPage(Object parameter) {
		return acg12SubjectSongDao.findListNewByPage(parameter);
	}
	
	public Long deletes(Object parameter){
		return acg12SubjectSongDao.deletes( parameter);
	}
	
	
}
