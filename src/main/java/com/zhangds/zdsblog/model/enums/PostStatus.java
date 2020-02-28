package com.zhangds.zdsblog.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create by zhangds
 * 2020-02-28 15:43
 **/
@Getter
@AllArgsConstructor
public enum PostStatus implements BaseEnum{

    PUBLISHED("0", "已发布"),

    DRAFT("1", "草稿"),

    RECYCLE("2", "回收站");

    private String code;

    private String name;

}
