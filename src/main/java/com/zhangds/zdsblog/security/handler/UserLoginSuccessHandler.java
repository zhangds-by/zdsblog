package com.zhangds.zdsblog.security.handler;

import com.zhangds.zdsblog.config.JwtConfig;
import com.zhangds.zdsblog.security.entity.SelfUserEntity;
import com.zhangds.zdsblog.utils.JwtUtils;
import com.zhangds.zdsblog.utils.ResultUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录成功处理类
 * Create by zhangds
 * 2020-02-26 10:15
 **/
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 组装JWT
        SelfUserEntity selfUserEntity =  (SelfUserEntity) authentication.getPrincipal();
        String token = JwtUtils.createAccessToken(selfUserEntity);
        token = JwtConfig.tokenPrefix + token;
        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code", "200");
        resultData.put("msg", "登录成功");
        resultData.put("token", token);
        ResultUtil.responseJson(response, resultData);
    }
}
