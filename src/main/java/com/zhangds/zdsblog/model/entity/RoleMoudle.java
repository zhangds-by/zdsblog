package com.zhangds.zdsblog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色-菜单
 * Create by zhangds
 * 2020-02-25 18:51
 **/
@Data
@Entity
@NoArgsConstructor
@IdClass(RoleMoudleId.class)
@Table(name = "sys_role_moudle")
public class RoleMoudle {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Id
    @Column(name = "moudle_id")
    private Long moudleId;

}
