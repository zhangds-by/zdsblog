package com.zhangds.zdsblog.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Create by zhangds
 * 2020-03-04 17:16
 **/
@Data
@Component
@ConfigurationProperties(prefix = "blog.security", ignoreUnknownFields=true)
public class SecurityProperties {
    // 登录 URL
    private String loginUrl;
    // 免认证静态资源路径
    private String anonResourcesUrl;
    // 记住我超时时间
    private int rememberMeTimeout;
    // 登出 URL
    private String logoutUrl;
    // 主页 URL
    private String indexUrl;

    //配置图片验证
    private ValidateCodeProperties code = new ValidateCodeProperties();

    //配置session
    private SessionProperties session = new SessionProperties();
}
