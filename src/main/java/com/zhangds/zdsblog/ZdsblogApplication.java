package com.zhangds.zdsblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = "com.zhangds.zdsblog.*")
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
@EntityScan(basePackages = "com.zhangds.zdsblog.model.entity")
@EnableJpaRepositories(basePackages = "com.zhangds.zdsblog.repository")
public class ZdsblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZdsblogApplication.class, args);
    }

}
