package com.acg12.entity.dto;

import com.acg12.entity.po.SystemAclEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemAclDTO {
	
	
	private String id ;
	
	private String text;
	
	private String icon;
	
	private boolean children;

	
	public List<SystemAclDTO> build(List<SystemAclEntity> acls){
		List<SystemAclDTO> dtos = new ArrayList<SystemAclDTO>();
		for (SystemAclEntity acl : acls) {
			dtos.add(new SystemAclDTO(acl.getId().toString(),acl.getName(), StringUtils.isEmpty(acl.getIcon()) ? "none" : acl.getIcon(),acl.getParentId() == null ? true : CollectionUtils.isNotEmpty(acl.getChildren()) ));
		}
		return dtos;
	}
}
