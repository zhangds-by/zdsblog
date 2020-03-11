package com.zhangds.zdsblog.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证异常处理
 * Create by zhangds
 * 2020-03-04 18:33
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String message) {
        super(message);
    }

}
