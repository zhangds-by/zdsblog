package com.zhangds.zdsblog.common.model.enums;

import com.zhangds.zdsblog.common.model.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create by zhangds
 * 2020-02-27 18:35
 **/
@Getter
@AllArgsConstructor
public enum LoginStatus implements BaseEnum {

    ENABLE("1", "启用"),

    DISABLE("0", "禁用"),

    DELETE("-1", "删除");

    private String code;

    private String name;

}
