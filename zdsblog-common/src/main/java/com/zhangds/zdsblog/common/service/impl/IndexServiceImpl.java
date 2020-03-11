package com.zhangds.zdsblog.common.service.impl;

import com.zhangds.zdsblog.common.model.entity.User;
import com.zhangds.zdsblog.common.model.entity.UserRole;
import com.zhangds.zdsblog.common.model.enums.LoginStatus;
import com.zhangds.zdsblog.common.service.IndexService;
import com.zhangds.zdsblog.common.service.RoleService;
import com.zhangds.zdsblog.common.service.UserRoleService;
import com.zhangds.zdsblog.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User register(User user) {
        //设置用户状态
        user.setStatus(LoginStatus.ENABLE.getCode());
        user.setCreateTime(new Date());
        User resUser = userService.save(user);
        //设置默认角色
        UserRole userRole = new UserRole();
        if (StringUtils.isEmpty(resUser)){
            userRole.setRoleId(2L);
            userRole.setUserId(resUser.getUserId());
        }
        userRoleService.save(userRole);
        return resUser;
    }

}
