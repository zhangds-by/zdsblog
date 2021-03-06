package com.zhangds.zdsblog.common.config;


import com.zhangds.zdsblog.common.config.properties.AsyncTaskProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Create by zhangds
 * 2020-03-12 17:59
 **/
@Configuration
@EnableAsync
@Slf4j
public class AsyncExecutorPoolConfig implements AsyncConfigurer {

    @Autowired
    private AsyncTaskProperties properties;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(properties.getCorePoolSize());
        //最大线程数
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        //队列容量
        executor.setQueueCapacity(properties.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        //线程名字前缀
        executor.setThreadNamePrefix(properties.getThreadNamePrefix());
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> {
            log.error("===="+throwable.getMessage()+"====", throwable);
            log.error("exception method:" + method.getName());
        };
    }
}
