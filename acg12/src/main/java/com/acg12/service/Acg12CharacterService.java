package com.acg12.service;

import com.acg12.entity.dto.Acg12CharacterDto;
import com.acg12.entity.dto.Acg12PersonDto;
import com.acg12.entity.po.Acg12CharacterEntity;

import java.util.List;

/**
 * SERVICE - Acg12Character(character)
 *
 * @author kcj
 * @version 2.0
 */
public interface Acg12CharacterService extends GenericService<Acg12CharacterEntity, Long> {
    public List<Acg12CharacterEntity> findListByPage(Object parameter);

    public List<Acg12CharacterEntity> findListNewByPage(Object parameter);

    public Long deletes(Object parameter);

    public Acg12CharacterDto findCharacterDto(long cId);

    public Long savaCharacterDto(Acg12CharacterDto characterDto);

    public Long deleteCharacterDto(Acg12CharacterDto characterDto);
}
