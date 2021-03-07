package com.west.auth.security.service;

import com.west.auth.security.entity.AuthUser;
import com.west.auth.security.entity.OnlineUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 在线用户 service
 *
 * @author west
 * @date 2021/3/1 17:22
 */
public interface OnlineService {

    /**
     * 查询用户
     *
     * @param key /
     * @return /
     */
    OnlineUser getByKey(String key);

    void save(AuthUser authUser, String token, HttpServletRequest request);

    void checkLoginOnUser(String userName, String ignoreToken);
}
