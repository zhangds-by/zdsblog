package com.zhangds.zdsblog.common.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Create by zhangds
 * 2020-02-25 18:51
 **/
@Data
@Entity
@NoArgsConstructor
@IdClass(UserRoleId.class)
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
@Table(name = "sys_user_role")
public class UserRole {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "role_id")
    private Long roleId;

    /*@EmbeddedId
    private UserRoleId id;*/

    /*@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    private Role role;*/

}
