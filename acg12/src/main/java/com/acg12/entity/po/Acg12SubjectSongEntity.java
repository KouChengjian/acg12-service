package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity - subjectSong
 * 
 * @author kcj
 * @version 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ACG12_SUBJECT_SONG")
public class Acg12SubjectSongEntity implements GenericEntity {

	private static final long serialVersionUID = 5081846432919091193L;

	/**  */
	@Column(id = true, name = "id", updatable = false)
	private Integer id;
	
	/**  */
	@Column(name = "subject_id" )
	private Integer subjectId;
	
	/**  */
	@Column(name = "s_id" )
	private Integer sId;
	
	/** 标题 */
	@Column(name = "title" )
	private String title;
	
}
