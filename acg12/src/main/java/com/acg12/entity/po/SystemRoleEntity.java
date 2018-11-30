package com.acg12.entity.po;

import com.framework.loippi.mybatis.eitity.GenericEntity;
import com.framework.loippi.mybatis.ext.annotation.Column;
import com.framework.loippi.mybatis.ext.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: KCJ
 * Date: 2018/10/17 9:51
 * Description: 角色
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SYSTEM_ROLE")
public class SystemRoleEntity implements GenericEntity {

    private static final long serialVersionUID = -4449299600117715568L;

    /** ID */
    @Column(id = true, name = "ID", updatable = false)
    private Long id;

    /** 创建日期 */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /** 创建者 */
    @Column(name = "CREATOR")
    private Long creator;

    /** 更新日期 */
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    /** 更新者 */
    @Column(name = "UPDATOR")
    private Long updator;

    /** 名称 */
    @Column(name = "ROLE_NAME")
    private String name;

    /** 是否内置 */
    @Column(name = "IS_SYSTEM")
    private Boolean isSystem;

    /** 描述 */
    @Column(name = "DESCRIPTIONS")
    private String description;

    /** 权限 */
    private Set<SystemAclEntity> authorities = new HashSet<SystemAclEntity>();


    public List<Long> getAclIds(){
        List<Long> ids = new ArrayList<Long>();
        for (SystemAclEntity acl : authorities) {
            ids.add(acl.getId());
        }
        return ids;
    }
}
