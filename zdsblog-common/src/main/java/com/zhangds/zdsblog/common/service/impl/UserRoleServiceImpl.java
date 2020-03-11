package com.zhangds.zdsblog.common.service.impl;

import com.zhangds.zdsblog.common.model.entity.UserRole;
import com.zhangds.zdsblog.common.model.entity.UserRoleId;
import com.zhangds.zdsblog.common.repository.UserRoleRepository;
import com.zhangds.zdsblog.common.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by zhangds
 * 2020-02-26 14:45
 **/
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRole> saveList(List<UserRole> list) {
        return null;
    }

    @Override
    public UserRole getByIds(UserRoleId ids) {
        return userRoleRepository.getOne(ids);
    }
}
