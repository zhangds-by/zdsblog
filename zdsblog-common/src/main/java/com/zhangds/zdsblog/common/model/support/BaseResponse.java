package com.zhangds.zdsblog.common.model.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

/**
 * Create by zhangds
 * 2020-02-17 14:11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    // 成功
    private static final Integer SUCCESS = 0;
    // 警告
    private static final Integer WARN = 1;
    // 异常 失败
    private static final Integer FAIL = 500;
    // 未认证
    private static final Integer UNAUTHORIZED = 401;
    // 超频
    private static final Integer OVERCLOCKING = 666;

    private Integer status;

    private String message;

    private T data;

    public BaseResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    @NonNull
    public static <T> BaseResponse<T> ok(@Nullable String message, @Nullable T data) {
        return new BaseResponse<>(HttpStatus.OK.value(), message, data);
    }

    @NonNull
    public static <T> BaseResponse<T> ok(@Nullable String message) {
        return ok(message, null);
    }

    public static <T> BaseResponse<T> ok(@NonNull T data) {
        return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    @NonNull
    public static <T> BaseResponse<T> ok(){
        return ok("success");
    }

    @NonNull
    public static <T> BaseResponse<T> fail(@Nullable String message) {
        return ok(message, null);
    }

    @NonNull
    public static <T> BaseResponse<T> fail(){
        return fail("fail");
    }

    public static <T> BaseResponse<T> warn(String message){
        return new BaseResponse<>(WARN, message);
    }

    public static <T> BaseResponse<T> unAuthorized(String message){
        return new BaseResponse<>(UNAUTHORIZED, message);
    }

    public static <T> BaseResponse<T> overClocking(String message){
        return new BaseResponse<>(OVERCLOCKING, message);
    }

}
