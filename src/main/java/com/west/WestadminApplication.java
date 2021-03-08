package com.west;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;

/**
 * emos 启动类
 *
 * @author west
 * @date 2020/1/29 14:36
 */
@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
public class WestadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WestadminApplication.class, args);
    }
}
