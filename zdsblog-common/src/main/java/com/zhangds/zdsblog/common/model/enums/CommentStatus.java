package com.zhangds.zdsblog.common.model.enums;

import com.zhangds.zdsblog.common.model.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create by zhangds
 * 2020-03-03 14:27
 **/
@Getter
@AllArgsConstructor
public enum  CommentStatus implements BaseEnum {

    PUBLISHED("0", "已发布"),

    AUDITING("1", "审核中"),

    RECYCLE("2", "回收站");

    private String name;

    private String code;
}
