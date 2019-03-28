package com.acg12.service.impl;

import com.acg12.dao.Acg12CollectPaletteDao;
import com.acg12.entity.dto.Acg12PaletteDto;
import com.acg12.entity.po.Acg12CollectPaletteEntity;
import com.acg12.service.Acg12CollectPaletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SERVICE - Acg12CollectPaletteEntity(CollectPalette)
 *
 * @author kcj
 * @version 2.0
 */
@Service
public class Acg12CollectPaletteServiceImpl extends GenericServiceImpl<Acg12CollectPaletteEntity, Long> implements Acg12CollectPaletteService {

    @Autowired
    private Acg12CollectPaletteDao acg12CollectPaletteDao;


    @Autowired
    public void setGenericDao() {
        super.setGenericDao(acg12CollectPaletteDao);
    }

    public List<Acg12CollectPaletteEntity> findListByPage(Object parameter) {
        return acg12CollectPaletteDao.findListByPage(parameter);
    }

    public List<Acg12CollectPaletteEntity> findListNewByPage(Object parameter) {
        return acg12CollectPaletteDao.findListNewByPage(parameter);
    }

    public Long deletes(Object parameter) {
        return acg12CollectPaletteDao.deletes(parameter);
    }

    @Override
    public List<Acg12PaletteDto> buildHasCollectPalette(List<Acg12PaletteDto> acg12PaletteDtoList, long userId) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("userId", userId);
        parameter.put("pageNumber", 0);
        parameter.put("pageSize", 1000);
        parameter.put("order", " id desc");
        List<Acg12CollectPaletteEntity> paletteEntityList = findListByPage(parameter);
        Map<String, Acg12CollectPaletteEntity> collectSubjectMap = paletteEntityList.stream().collect(Collectors.toMap(Acg12CollectPaletteEntity::getBoardId, a -> a, (k1, k2) -> k1));
        acg12PaletteDtoList = acg12PaletteDtoList.stream().map(e -> {
            Acg12CollectPaletteEntity acg12CollectAlbumEntity = collectSubjectMap.get(e.getBoardId());
            if (acg12CollectAlbumEntity != null) {
                e.setIsCollect(1);
            } else {
                e.setIsCollect(0);
            }
            return e;
        }).collect(Collectors.toList());
        return acg12PaletteDtoList;
    }


}
