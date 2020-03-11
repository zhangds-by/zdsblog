package com.zhangds.zdsblog.common.model.param;

import com.zhangds.zdsblog.common.model.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create by zhangds
 * 2020-02-25 15:25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserParam extends BaseParam {

    private String username;
}
