package com.zhangds.zdsblog.security;

import com.zhangds.zdsblog.common.model.entity.Role;
import com.zhangds.zdsblog.common.model.entity.User;
import com.zhangds.zdsblog.common.model.enums.LoginStatus;
import com.zhangds.zdsblog.common.service.UserService;
import com.zhangds.zdsblog.security.entity.SelfUserEntity;
import com.zhangds.zdsblog.security.service.SelfUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 使用用户名和密码登录验证
 * Create by zhangds
 * 2020-02-26 10:24
 **/
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SelfUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单输入中返回的用户名
        String userName = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();
        // 查询用户是否存在
        SelfUserEntity userInfo = userDetailsService.loadUserByUsername(userName);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!new BCryptPasswordEncoder().matches(password, userInfo.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        // 还可以加一些其他信息的判断，比如用户账号已停用等判断
        if (userInfo.getStatus().equals(LoginStatus.DISABLE)){
            throw new LockedException("该用户已被禁用");
        }
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<Role> roleList = userService.getRoleByUserId(userInfo.getUserId());
        for (Role role: roleList){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleCode()));
        }
        userInfo.setAuthorities(authorities);
        // 进行登录
        return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
