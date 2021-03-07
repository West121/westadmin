package com.west.auth.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 在线用户
 *
 * @author west
 * @date 2021/3/2 10:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineUser implements Serializable {

    @Serial
    private static final long serialVersionUID = -4159418518971521874L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 部门
     */
    private String dept;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * IP
     */
    private String ip;

    /**
     * 地址
     */
    private String address;

    /**
     * token
     */
    private String key;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;
}
