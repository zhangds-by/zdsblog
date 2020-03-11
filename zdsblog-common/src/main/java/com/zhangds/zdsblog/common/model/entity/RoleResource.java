package com.zhangds.zdsblog.common.model.entity;

import com.zhangds.zdsblog.common.model.entity.RoleResourceId;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 角色-api资源
 * Create by zhangds
 * 2020-02-25 18:51
 **/
@Data
@Entity
@NoArgsConstructor
@IdClass(RoleResourceId.class)
@Table(name = "sys_role_resource")
public class RoleResource {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Id
    private Long roleId;

    @Id
    private Long resourceId;

}
