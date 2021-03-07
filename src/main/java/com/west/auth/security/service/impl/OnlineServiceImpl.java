package com.west.auth.security.service.impl;

import cn.hutool.core.util.StrUtil;
import com.west.auth.security.entity.AuthUser;
import com.west.auth.security.entity.OnlineUser;
import com.west.auth.security.properties.SecurityProperties;
import com.west.auth.security.service.OnlineService;
import com.west.comm.util.EncryptUtils;
import com.west.comm.util.NetUtils;
import com.west.comm.util.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 在线用户 service 实现
 *
 * @author west
 * @date 2021/3/1 17:50
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineServiceImpl implements OnlineService {

    private final RedisUtils redisUtils;
    private final SecurityProperties securityProperties;

    @Override
    public OnlineUser getByKey(String key) {
        return (OnlineUser) redisUtils.get(key);
    }

    @Override
    public void save(AuthUser authUser, String token, HttpServletRequest request) {
        String ip = NetUtils.getIp(request);
        String browser = NetUtils.getBrowser(request);
        String address = NetUtils.getCityInfo(ip);
        OnlineUser onlineUser = null;
        try {
            onlineUser = new OnlineUser(authUser.getUsername(), authUser.getNickname(), "dept", browser, ip, address, EncryptUtils.desEncrypt(token), LocalDateTime.now());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        redisUtils.set(securityProperties.getOnlineKey() + token, onlineUser, securityProperties.getTokenValidityInSeconds() / 1000);
    }

    @Override
    public void checkLoginOnUser(String userName, String ignoreToken) {
        List<OnlineUser> onlineUsers = getAll(userName);
        if (onlineUsers == null || onlineUsers.isEmpty()) {
            return;
        }
        for (OnlineUser onlineUser : onlineUsers) {
            if (onlineUser.getUsername().equals(userName)) {
                try {
                    String token = EncryptUtils.desDecrypt(onlineUser.getKey());
                    if (StrUtil.isNotBlank(ignoreToken) && !ignoreToken.equals(token)) {
                        this.kickOut(token);
                    } else if (StrUtil.isBlank(ignoreToken)) {
                        this.kickOut(token);
                    }
                } catch (Exception e) {
                    log.error("checkUser is error", e);
                }
            }
        }
    }

    public List<OnlineUser> getAll(String filter) {
        List<String> keys = redisUtils.scan(securityProperties.getOnlineKey() + "*");
        Collections.reverse(keys);
        List<OnlineUser> onlineUsers = new ArrayList<>();
        for (String key : keys) {
            OnlineUser onlineUser = (OnlineUser) redisUtils.get(key);
            if (StrUtil.isNotBlank(filter)) {
                if (onlineUser.toString().contains(filter)) {
                    onlineUsers.add(onlineUser);
                }
            } else {
                onlineUsers.add(onlineUser);
            }
        }
        onlineUsers.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUsers;
    }

    public void kickOut(String key) {
        key = securityProperties.getOnlineKey() + key;
        redisUtils.del(key);
    }
}
