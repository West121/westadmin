package com.west.auth.security.config;

import com.west.auth.security.properties.LoginProperties;
import com.west.auth.security.properties.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author west
 * @date 2021/3/4 15:48
 */
@Configuration
public class AuthPropertiesConfig {

    @Bean
    @ConfigurationProperties(prefix = "auth.login")
    public LoginProperties loginProperties() {
        return new LoginProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "auth.jwt")
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }
}
