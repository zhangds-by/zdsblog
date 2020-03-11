package com.zhangds.zdsblog.common.service;

import com.zhangds.zdsblog.common.model.entity.UserRole;
import com.zhangds.zdsblog.common.model.entity.UserRoleId;

import java.util.List;

/**
 * Create by zhangds
 * 2020-02-26 11:40
 **/
public interface UserRoleService {

    UserRole save(UserRole userRole);

    List<UserRole> saveList(List<UserRole> list);

    UserRole getByIds(UserRoleId ids);
}
