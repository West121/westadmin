package com.west.comm.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author west
 * @date 2021/3/5 14:11
 */
@Data
@Component
public class RsaProperties {

    public static String privateKey;

    @Value("${auth.rsa.private_key}")
    public void setPrivateKey(String privateKey) {
        RsaProperties.privateKey = privateKey;
    }
}
