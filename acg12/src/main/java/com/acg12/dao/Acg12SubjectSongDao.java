package com.acg12.dao;

import com.acg12.entity.po.Acg12SubjectSongEntity;
import com.framework.loippi.mybatis.dao.GenericDao;
import java.util.List;

/**
 * DAO - Acg12SubjectSong(subjectSong)
 * 
 * @author kcj
 * @version 2.0
 */
public interface Acg12SubjectSongDao  extends GenericDao<Acg12SubjectSongEntity, Long> {
	List<Acg12SubjectSongEntity> findListByPage(Object parameter);
	List<Acg12SubjectSongEntity> findListNewByPage(Object parameter);
    Long deletes(Object parameter);
}
