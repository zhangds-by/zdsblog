package com.zhangds.zdsblog.service.impl;

import com.zhangds.zdsblog.model.entity.Role;
import com.zhangds.zdsblog.repository.RoleRepository;
import com.zhangds.zdsblog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by zhangds
 * 2020-02-26 17:21
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.getOne(Integer.valueOf(roleId.intValue()));
    }

    @Override
    public Role update(Role role) {
        return null;
    }
}
