package com.zhangds.zdsblog.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private Long id;

    private Long userId;

    private Date createTime;

    private String ip;

    private String address;

    @ApiModelProperty(value = "浏览器登录类型")
    private String userAgent;
}
