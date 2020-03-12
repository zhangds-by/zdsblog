package com.zhangds.zdsblog.common.service;

import com.zhangds.zdsblog.common.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Create by zhangds
 * 2020-02-17 14:28
 **/
public interface AdminService {
    List<User> getList();
}
