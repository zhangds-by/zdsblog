package com.zhangds.zdsblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableTransactionManagement
@EnableJpaAuditing
@EntityScan(basePackages = {"com.zhangds.zdsblog.*", "com.zhangds.generator.*"})
@EnableJpaRepositories(basePackages = {"com.zhangds.zdsblog.*", "com.zhangds.generator.*"})
@ComponentScan(basePackages = {"com.zhangds.zdsblog.*", "com.zhangds.zdsblog.common.**",
        "com.zhangds.zdsblog.security.*", "com.zhangds.generator.**", "com.zhangds.zdsblog.aop.**"})
@SpringBootApplication
public class ZdsblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZdsblogApplication.class, args);
    }

}
