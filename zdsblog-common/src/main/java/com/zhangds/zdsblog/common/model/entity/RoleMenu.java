package com.zhangds.zdsblog.common.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Create by zhangds
 * 2020-03-10 15:55
 **/
@Data
@Entity
@NoArgsConstructor
@IdClass(RoleMenuId.class)
@Table(name = "sys_role_menu")
public class RoleMenu {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Id
    private Long roleId;

    @Id
    private Long menuId;
}
