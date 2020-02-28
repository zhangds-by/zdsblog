package com.zhangds.zdsblog.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zhangds.zdsblog.model.entity.Role;
import com.zhangds.zdsblog.model.entity.User;
import com.zhangds.zdsblog.model.entity.UserRole;
import com.zhangds.zdsblog.model.enums.LoginStatus;
import com.zhangds.zdsblog.security.entity.SelfUserEntity;
import com.zhangds.zdsblog.security.service.SelfUserDetailsService;
import com.zhangds.zdsblog.service.IndexService;
import com.zhangds.zdsblog.service.RoleService;
import com.zhangds.zdsblog.service.UserRoleService;
import com.zhangds.zdsblog.service.UserService;
import com.zhangds.zdsblog.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Create by zhangds
 * 2020-02-27 16:37
 **/
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SelfUserDetailsService userDetailsService;

    @Override
    public String login(String username, String password) {
        String token = null;
        SelfUserEntity userDetails = userDetailsService.loadUserByUsername(username);
        //UserAuthenticationProvider userAuthenticationProvider = new UserAuthenticationProvider();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        //userAuthenticationProvider.authenticate(userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token = JwtUtils.createAccessToken(userDetails);
        return token;
    }

    @Override
    public User register(User user) {
        //查询是否有相同用户
        User sameUser = null;
        if (StrUtil.isNotEmpty(user.getUserName())){
             sameUser = userService.getUserByName(user.getUserName());
        }
        if (ObjectUtil.isNotEmpty(sameUser)){
            return null;
        }
        //密码加密
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //设置用户状态
        user.setStatus(LoginStatus.ENABLE.getCode());
        user.setCreateTime(new Date());
        User resUser = userService.save(user);
        //设置默认角色
        UserRole userRole = new UserRole();
        Role role = roleService.getRoleById(2L);
        if (StringUtils.isEmpty(resUser)){
            //userRole.setId(new UserRole(resUser.getUserId(), 2L));
        }
        userRoleService.save(userRole);
        return resUser;
    }
}
