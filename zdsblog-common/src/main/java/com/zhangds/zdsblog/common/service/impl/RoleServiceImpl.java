package com.zhangds.zdsblog.common.service.impl;

import com.zhangds.zdsblog.common.model.entity.Role;
import com.zhangds.zdsblog.common.repository.RoleRepository;
import com.zhangds.zdsblog.common.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.getOne(roleId);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.saveAndFlush(role);
    }
}
