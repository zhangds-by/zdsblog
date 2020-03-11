package com.zhangds.zdsblog.security.properties;

import lombok.Data;

/**
 * 验证图片信息配置
 * Create by zhangds
 * 2020-03-04 17:30
 **/
@Data
public class ImageCodeProperties {

    private int width = 67;

    private int height = 23;

    private String createUrl = "";

    // 处理使用图形验证码认证 URL
    private String loginProcessingUrl = "";

    private String url = "";

    private int length = 6;

    private int expireIn = 60;
}
