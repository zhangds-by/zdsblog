package com.zhangds.zdsblog.security.service;

import com.zhangds.zdsblog.model.entity.User;
import com.zhangds.zdsblog.security.entity.SelfUserEntity;
import com.zhangds.zdsblog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 安全业务实现
 * Create by zhangds
 * 2020-02-26 10:26
 **/
@Service
public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public SelfUserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        User user =userService.getUserByName(username);
        if (user!=null){
            // 组装参数
            SelfUserEntity selfUserEntity = new SelfUserEntity();
            BeanUtils.copyProperties(user, selfUserEntity);
            return selfUserEntity;
        }
        return null;
    }
}
