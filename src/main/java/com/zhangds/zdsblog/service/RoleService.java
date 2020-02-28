package com.zhangds.zdsblog.service;

import com.zhangds.zdsblog.model.entity.Role;

/**
 * Create by zhangds
 * 2020-02-26 11:39
 **/
public interface RoleService {

    Role save(Role role);

    Role getRoleById(Long roleId);

    Role update(Role role);
}
