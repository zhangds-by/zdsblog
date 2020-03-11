package com.zhangds.zdsblog.security.handler;

import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.utils.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当未登录或者token失效访问接口
 * Create by zhangds
 * 2020-02-26 10:09
 **/
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResultUtil.responseJson(response, BaseResponse.unAuthorized("未登录或登录过时"));
    }
}
