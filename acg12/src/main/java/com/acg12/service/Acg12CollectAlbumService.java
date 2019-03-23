package com.acg12.service;

import com.acg12.entity.dto.Acg12AlbumDto;
import com.acg12.entity.po.Acg12CollectAlbumEntity;

import java.util.List;

/**
 * SERVICE - Acg12CollectAlbumEntity(CollectAlbum)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12CollectAlbumService extends GenericService<Acg12CollectAlbumEntity, Long> {
    public List<Acg12CollectAlbumEntity> findListByPage(Object parameter);

    public List<Acg12CollectAlbumEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);

    public List<Acg12AlbumDto> buildHasCollectAlbum(List<Acg12AlbumDto> acg12AlbumDtoList, long userId);
}
