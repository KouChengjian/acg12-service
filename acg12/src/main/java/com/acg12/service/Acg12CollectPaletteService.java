package com.acg12.service;

import com.acg12.entity.dto.Acg12PaletteDto;
import com.acg12.entity.po.Acg12CollectPaletteEntity;

import java.util.List;

/**
 * SERVICE - Acg12CollectPaletteEntity(CollectPalette)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12CollectPaletteService extends GenericService<Acg12CollectPaletteEntity, Long> {
    public List<Acg12CollectPaletteEntity> findListByPage(Object parameter);

    public List<Acg12CollectPaletteEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);

    public List<Acg12PaletteDto> buildHasCollectPalette(List<Acg12PaletteDto> acg12PaletteDtoList, long userId);
}
