package com.zhangds.zdsblog.common.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Create by zhangds
 * 2020-02-25 18:44
 **/
@Data
@Entity
@Table(name = "sys_role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @ApiModelProperty("角色编码")
    @Column(name = "role_code", columnDefinition = "varchar(20)")
    private String roleCode;

    @ApiModelProperty("角色名称")
    @Column(name = "role_name", columnDefinition = "varchar(50)")
    private String roleName;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date createTime;

    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;

    /*@ElementCollection
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> users;*/

    /*@ElementCollection
    private List<User> users;

    @JsonIgnore
    @OneToMany()
    @JoinTable(name = "user_role", //中间表名字
            joinColumns = {
                    @JoinColumn(name="roleId", referencedColumnName = "roleId") //配置本表与中间表对应关系
            },
            inverseJoinColumns ={
                    @JoinColumn(name = "userId",referencedColumnName = "userId")//配置中间表另一字段与对应表关联关系
            })
    public List<User> getUsers() {
        return users;
    }*/
}
