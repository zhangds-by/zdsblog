package com.zhangds.zdsblog.security.handler;

import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理
 * Create by zhangds
 * 2020-02-26 10:12
 **/
@Slf4j
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (e instanceof UsernameNotFoundException){
            log.info("【登录失败】" + e.getMessage());
            ResultUtil.responseJson(response, BaseResponse.fail("用户不存在"));
        }
        if (e instanceof LockedException){
            log.info("【登录失败】" + e.getMessage());
            ResultUtil.responseJson(response, BaseResponse.fail("用户已被禁用"));
        }
        if (e instanceof BadCredentialsException){
            log.info("【登录失败】" + e.getMessage());
            ResultUtil.responseJson(response, BaseResponse.fail("用户名密码不正确"));
        }
        ResultUtil.responseJson(response, BaseResponse.fail("登录失败"));
    }
}
