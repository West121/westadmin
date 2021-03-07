package com.west.auth.security.util;

import cn.hutool.core.util.StrUtil;
import com.west.auth.security.service.impl.UserDetailsServiceImpl;
import org.springframework.stereotype.Component;

/**
 * @author west
 * @date 2021/3/4 17:08
 */
@Component
public class OnlineUserCacheUtils {

    /**
     * 清理特定用户缓存信息<br>
     * 用户信息变更时
     *
     * @param userName /
     */
    public void cleanUserCache(String userName) {
        if (StrUtil.isNotEmpty(userName)) {
            UserDetailsServiceImpl.userDtoCache.remove(userName);
        }
    }

    /**
     * 清理所有用户的缓存信息<br>
     * ,如发生角色授权信息变化，可以简便的全部失效缓存
     */
    public void cleanAll() {
        UserDetailsServiceImpl.userDtoCache.clear();
    }
}
