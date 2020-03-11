package com.zhangds.zdsblog.security.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 验证方式配置
 * Create by zhangds
 * 2020-03-04 18:20
 **/
@Data
@NoArgsConstructor
public class ValidateCodeProperties {

    //获取图片验证配置
    private ImageCodeProperties image = new ImageCodeProperties();
}
