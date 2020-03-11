package com.zhangds.zdsblog.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 全局异常处理
 * Create by zhangds
 * 2020-03-09 12:22
 **/
@Getter
public class GlobalRequestException extends RuntimeException{

    private Integer status = BAD_REQUEST.value();

    public GlobalRequestException(String msg){
        super(msg);
    }

    public GlobalRequestException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
