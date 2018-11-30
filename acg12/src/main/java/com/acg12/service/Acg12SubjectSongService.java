package com.acg12.service;

import com.acg12.entity.po.Acg12SubjectSongEntity;

import java.util.List;

/**
 * SERVICE - Acg12SubjectSong(subjectSong)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12SubjectSongService extends GenericService<Acg12SubjectSongEntity, Long> {
    public List<Acg12SubjectSongEntity> findListByPage(Object parameter);

    public List<Acg12SubjectSongEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);
}
