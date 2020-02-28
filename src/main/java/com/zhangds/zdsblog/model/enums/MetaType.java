package com.zhangds.zdsblog.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create by zhangds
 * 2020-02-28 16:53
 **/
@Getter
@AllArgsConstructor
public enum MetaType implements BaseEnum {
    ORIGINAL("0", "原创"),

    TRANSTER("1", "转载"),

    TRANSLATE("2", "翻译");

    private String code;

    private String name;
}
