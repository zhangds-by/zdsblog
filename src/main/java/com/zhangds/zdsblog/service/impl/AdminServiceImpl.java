package com.zhangds.zdsblog.service.impl;

import com.zhangds.zdsblog.model.entity.User;
import com.zhangds.zdsblog.repository.UserRepository;
import com.zhangds.zdsblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by zhangds
 * 2020-02-25 10:50
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getList() {
        return userRepository.findAll();
    }
}
