package com.zhangds.zdsblog.common.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 登录日志
 * Create by zhangds
 * 2020-02-28 11:04
 **/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_login_log")
public class LoginLog {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "bigint")
    private Long id;

    @Column(name = "username", columnDefinition = "varchar(50) not null")
    private String username;

    @Column(name = "login_time", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime;

    @Column(name = "ip", columnDefinition = "varchar(50)")
    private String ip;

    @Column(name = "address", columnDefinition = "varchar(100)")
    private String address;

    @ApiModelProperty(value = "浏览器登录类型")
    @Column(name = "user_agent", columnDefinition = "varchar(50)")
    private String userAgent;
}
