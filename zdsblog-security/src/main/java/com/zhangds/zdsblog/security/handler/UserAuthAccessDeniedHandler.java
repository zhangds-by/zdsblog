package com.zhangds.zdsblog.security.handler;

import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.common.utils.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 暂无权限处理类
 * Create by zhangds
 * 2020-02-26 09:59
 **/
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResultUtil.responseJson(response, BaseResponse.unAuthorized("未授权"));
    }
}
