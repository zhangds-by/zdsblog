package com.zhangds.zdsblog.common.model.dto;

import com.zhangds.zdsblog.common.model.entity.User;
import lombok.Data;

/**
 * Create by zhangds
 * 2020-03-06 11:18
 **/
@Data
public class UserLoginDTO extends User {

    private String token;
}
