package com.zhangds.zdsblog.security.session;

import com.zhangds.zdsblog.security.properties.SecurityProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理 session 失效
 * Create by zhangds
 * 2020-03-05 18:59
 **/
@Data
public class HandleInvalidSessionStrategy implements InvalidSessionStrategy {

    @Autowired
    private SecurityProperties securityProperties;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, securityProperties.getLogoutUrl());
    }

}
