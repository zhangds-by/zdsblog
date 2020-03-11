package com.zhangds.zdsblog.security.handler;

import com.zhangds.zdsblog.security.entity.UserLoginDTO;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import com.zhangds.zdsblog.security.config.JwtConfig;
import com.zhangds.zdsblog.security.entity.SelfUserEntity;
import com.zhangds.zdsblog.security.utils.JwtUtils;
import com.zhangds.zdsblog.common.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录成功处理类
 * Create by zhangds
 * 2020-02-26 10:15
 **/
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 组装JWT
        SelfUserEntity selfUserEntity =  (SelfUserEntity) authentication.getPrincipal();
        String token = JwtUtils.createAccessToken(selfUserEntity);
        token = JwtConfig.tokenPrefix + token;
        // 封装返回参数
        UserLoginDTO dto = new UserLoginDTO();
        BeanUtils.copyProperties(selfUserEntity, dto);
        dto.setToken(token);
        ResultUtil.responseJson(response, BaseResponse.ok(dto));
    }
}
