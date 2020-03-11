package com.zhangds.zdsblog.common.model.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create by zhangds
 * 2020-02-27 16:31
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginParam {

    private String username;

    private String password;

    private String imageCode;
}
