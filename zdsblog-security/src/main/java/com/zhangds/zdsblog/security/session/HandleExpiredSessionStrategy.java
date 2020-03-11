package com.zhangds.zdsblog.security.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangds.zdsblog.common.model.constants.SysContants;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Create by zhangds
 * 2020-03-06 09:43
 **/
public class HandleExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        event.getResponse().setContentType(SysContants.JSON_UTF8);
        event.getResponse().getWriter().write(objectMapper.writeValueAsString(BaseResponse.unAuthorized("登录已失效")));
    }
}
