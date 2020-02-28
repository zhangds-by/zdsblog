package com.zhangds.zdsblog.model.entity;

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
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Id
    @Column(name = "resource_id")
    private Long resourceId;

}
