package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - characterDetail
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_CHARACTER_DETAIL")
public class Acg12CharacterDetailEntity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/** id */
	@Column(id = true, name = "id", updatable = false)
	private Integer id;
	
	/** character_id */
	@Column(name = "character_id" )
	private Integer characterId;
	
	/** title */
	@Column(name = "other_title" )
	private String otherTitle;
	
	/** value */
	@Column(name = "other_value" )
	private String otherValue;
	
}
