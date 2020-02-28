package com.zhangds.zdsblog.service;

import com.zhangds.zdsblog.model.entity.User;

/**
 * Create by zhangds
 * 2020-02-27 16:33
 **/
public interface IndexService {

    String login(String username, String password);

    User register(User user);
}
