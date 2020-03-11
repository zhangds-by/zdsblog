package com.zhangds.zdsblog.security.handler;

import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.utils.ResultUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功处理类
 * Create by zhangds
 * 2020-02-26 10:20
 **/
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        ResultUtil.responseJson(response, BaseResponse.ok("登出成功"));
    }
}
