package com.zhangds.zdsblog.common.exception.handler;

import com.zhangds.zdsblog.common.exception.GlobalRequestException;
import com.zhangds.zdsblog.common.exception.file.FileLimitException;
import com.zhangds.zdsblog.common.model.support.BaseResponse;
import io.netty.util.internal.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * Create by zhangds
 * 2020-03-09 11:25
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Throwable.class)
    public BaseResponse handleException(Throwable e){
        // 打印堆栈信息
        log.error(ThrowableUtil.stackTraceToString(e));
        return BaseResponse.fail(e.getMessage());
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // 打印堆栈信息
        log.error(ThrowableUtil.stackTraceToString(e));
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String msg = "不能为空";
        if(msg.equals(message)){
            message = str[1] + ":" + message;
        }
        return BaseResponse.fail(message);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = GlobalRequestException.class)
    public BaseResponse badRequestException(GlobalRequestException e) {
        log.error(ThrowableUtil.stackTraceToString(e));
        return new BaseResponse(e.getStatus(), e.getMessage());
    }

    /**
     * 处理文件限制异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = FileLimitException.class)
    public BaseResponse fileLimitException(FileLimitException e) {
        log.error(ThrowableUtil.stackTraceToString(e));
        return new BaseResponse(e.getStatus(), e.getMessage());
    }
}
