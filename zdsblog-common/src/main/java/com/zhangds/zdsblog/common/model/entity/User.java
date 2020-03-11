package com.zhangds.zdsblog.common.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Create by zhangds
 * 2020-02-17 14:22
 **/
@Data
@Entity
@Table(name = "sys_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", columnDefinition = "bigint")
    private Long userId;

    @ApiModelProperty("用户账号")
    @Column(name = "username")
    private String username;

    @ApiModelProperty("昵称")
    @Column(name = "nick_name")
    private String nickName;

    @ApiModelProperty("密码")
    @Column(name = "password")
    private String password;

    @ApiModelProperty("邮箱")
    @Column(name = "email")
    private String email;

    @ApiModelProperty("电话号码")
    @Column(name = "phone")
    private String phone;

    @ApiModelProperty("个人描述")
    @Column(name = "description")
    private String description;


    @ApiModelProperty("注销时间")
    @Column(name = "expire_time")
    private Date expireTime;

    @ApiModelProperty("头像")
    @Column(name = "icon")
    private String icon;

    @ApiModelProperty("最近登录时间")
    @Column(name = "login_time")
    private Date loginTime;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "deleted")
    private Boolean deleted;

    @ApiModelProperty("用户状态 0-禁用 1-启用")
    @Column(name = "status")
    private String status;

    /*@ElementCollection
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles;*/

    /*@ElementCollection
    private List<Role> roles;

    @JsonIgnore
    @OneToMany()
    @JoinTable(name = "user_role", //中间表名字
            joinColumns = {
                    @JoinColumn(name="userId", referencedColumnName = "userId") //配置本表与中间表对应关系
            },
            inverseJoinColumns ={
                    @JoinColumn(name = "roleId", referencedColumnName = "roleId")//配置中间表另一字段与对应表关联关系
            })
    //所有JoinColumn 配置 第一个都是中间表的字段 第二个都是对应表的字段
    public List<Role> getRoles() {
        return roles;
    }*/
}
