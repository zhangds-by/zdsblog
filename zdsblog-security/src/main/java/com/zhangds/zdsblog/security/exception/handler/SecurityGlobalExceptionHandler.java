package com.zhangds.zdsblog.security.exception.handler;

import com.zhangds.zdsblog.common.model.support.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Create by zhangds
 * 2020-03-09 11:45
 **/
@Slf4j
@RestControllerAdvice
public class SecurityGlobalExceptionHandler {
    /**
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    public BaseResponse badCredentialsException(BadCredentialsException e){
        // 打印堆栈信息
        String message = "坏的凭证".equals(e.getMessage()) ? "用户名或密码不正确" : e.getMessage();
        log.error(message);
        return BaseResponse.unAuthorized(message);
    }
}
