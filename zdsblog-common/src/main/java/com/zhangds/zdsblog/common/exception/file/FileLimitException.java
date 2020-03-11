package com.zhangds.zdsblog.common.exception.file;

import com.zhangds.zdsblog.common.exception.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Create by zhangds
 * 2020-03-11 14:32
 **/
@Getter
public class FileLimitException extends RuntimeException {

    private Integer status = INTERNAL_SERVER_ERROR.value();

    public FileLimitException(String msg){
        super(msg);
    }

    public FileLimitException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
