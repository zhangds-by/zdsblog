package com.zhangds.zdsblog.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * Create by zhangds
 * 2020-02-25 18:38
 **/
@Data
public class SelfUserEntity implements Serializable, UserDetails {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态:NORMAL正常  PROHIBIT禁用
     */
    private String status;

    /**
     * 用户角色
     */

    private Collection<GrantedAuthority> authorities;
    /**
     * 账户是否过期
     */

    private boolean isAccountNonExpired = false;
    /**
     * 账户是否被锁定
     */

    private boolean isAccountNonLocked = false;
    /**
     * 证书是否过期
     */

    private boolean isCredentialsNonExpired = false;
    /**
     * 账户是否有效
     */

    private boolean isEnabled = true;

}
