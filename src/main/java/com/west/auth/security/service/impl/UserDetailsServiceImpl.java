package com.west.auth.security.service.impl;

import com.west.auth.security.properties.LoginProperties;
import com.west.auth.security.entity.AuthUser;
import com.west.comm.exception.MyException;
import com.west.sys.domain.dto.SysUserDTO;
import com.west.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author west
 * @date 2021/3/4 14:40
 */
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserService userService;
    private final LoginProperties loginProperties;

    public void setEnableCache(boolean enableCache) {
        this.loginProperties.setCacheEnable(enableCache);
    }

    /**
     * 用户信息缓存
     *
     * @see
     */
    public static Map<String, AuthUser> userDtoCache = new ConcurrentHashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean searchDb = true;
        AuthUser authUser = null;
        if (loginProperties.isCacheEnable() && userDtoCache.containsKey(username)) {
            authUser = userDtoCache.get(username);
            searchDb = false;
        }
        if (searchDb) {
            SysUserDTO userDTO = userService.getByUsername(username);

            if (userDTO == null) {
                throw new UsernameNotFoundException("");
            } else {
                if (!userDTO.getEnabled()) {
                    throw new MyException("账号未激活！");
                }
                authUser = new AuthUser();
                BeanUtils.copyProperties(userDTO, authUser);
                userDtoCache.put(username, authUser);
            }
        }
        return authUser;
    }
}
