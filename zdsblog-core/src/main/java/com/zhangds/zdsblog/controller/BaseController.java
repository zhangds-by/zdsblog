package com.zhangds.zdsblog.controller;

import com.zhangds.zdsblog.common.model.entity.User;
import com.zhangds.zdsblog.security.entity.SelfUserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Create by zhangds
 * 2020-03-11 11:07
 **/
public class BaseController {

    protected User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        User user = new User();
        if(principal instanceof SelfUserEntity){
            SelfUserEntity userDetails = (SelfUserEntity) principal;
            user.setUserId(userDetails.getUserId());
            user.setPassword(userDetails.getPassword());
            user.setUsername(userDetails.getUsername());
        }else{
            SelfUserEntity userDetails = (SelfUserEntity) principal;
            user.setUserId(userDetails.getUserId());
            user.setPassword(userDetails.getPassword());
            user.setUsername(userDetails.getUsername());
        }
        return user;
    }
}
