package com.zhangds.zdsblog.common.model.enums;

import com.zhangds.zdsblog.common.model.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create by zhangds
 * 2020-02-28 15:55
 **/
@Getter
@AllArgsConstructor
public enum CreateFrom implements BaseEnum {

    ADMIN("0", "用户后台"),

    //后期开发小程序
    WECHAT("1", "微信");

    private String code;

    private String name;
}
