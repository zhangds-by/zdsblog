package com.zhangds.zdsblog.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create by zhangds
 * 2020-03-11 14:22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String message;

}
