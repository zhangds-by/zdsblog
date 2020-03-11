package com.zhangds.zdsblog.security.properties;

import lombok.Data;

/**
 * Create by zhangds
 * 2020-03-04 17:36
 **/
@Data
public class SessionProperties {

    // 最大并发登录数量，默认值为-1，表示无限制
    private Integer maximumSessions = -1;
}
