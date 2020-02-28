package com.zhangds.zdsblog.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create by zhangds
 * 2020-02-28 16:49
 **/
@Getter
@AllArgsConstructor
public enum OptionType implements BaseEnum {

    PRIVATE("0", "仅自己可见"),

    PUBLIC("1", "公开"),

    ONLY_LIKE("2", "关注可见"),

    VIP("3", "vip可见");

    private String code;

    private String name;
}
