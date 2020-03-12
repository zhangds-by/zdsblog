package com.zhangds.zdsblog.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Create by zhangds
 * 2020-03-12 18:03
 **/
@Data
@Component
@ConfigurationProperties(prefix = "blog.task.pool")
public class AsyncTaskProperties {
    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;

    private String threadNamePrefix;
}
