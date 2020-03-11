package com.zhangds.zdsblog.security.handler;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登出处理session
 * Create by zhangds
 * 2020-03-06 10:08
 **/
@Data
public class UserLogoutHandler implements LogoutHandler {

    private SessionRegistry sessionRegistry;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String sessionId = request.getRequestedSessionId();
        if (sessionId != null){
            sessionRegistry.removeSessionInformation(sessionId);
        }
    }
}
