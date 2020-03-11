package com.zhangds.zdsblog.security;

import com.zhangds.zdsblog.common.model.entity.Menu;
import com.zhangds.zdsblog.common.service.MenuService;
import com.zhangds.zdsblog.common.service.UserService;
import com.zhangds.zdsblog.security.entity.SelfUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义权限注解验证
 * Create by zhangds
 * 2020-02-26 11:04
 **/
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private MenuService menuService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        // 获取用户信息
        SelfUserEntity selfUserEntity =(SelfUserEntity) authentication.getPrincipal();
        // 查询用户权限(这里可以将权限放入缓存中提升效率)
        Set<String> permissions = new HashSet<>();
        List<Menu> menuList = menuService.getListByUserId(selfUserEntity.getUserId(), null);
        for (Menu menu : menuList) {
            permissions.add(menu.getPerms());
        }
        // 权限对比
        if (permissions.contains(permission.toString())){
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
