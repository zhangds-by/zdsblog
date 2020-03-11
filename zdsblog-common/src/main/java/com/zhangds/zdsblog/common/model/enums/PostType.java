package com.zhangds.zdsblog.common.model.enums;

import com.zhangds.zdsblog.common.model.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 发布类型
 * Create by zhangds
 * 2020-02-28 16:49
 **/
@Getter
@AllArgsConstructor
public enum PostType implements BaseEnum {

    PRIVATE("0", "仅自己可见"),

    PUBLIC("1", "公开"),

    ONLY_LIKE("2", "关注可见"),

    VIP("3", "vip可见");

    private String code;

    private String name;
}
