package com.west.auth.security.filter;

import cn.hutool.core.util.StrUtil;
import com.west.auth.security.entity.AuthUser;
import com.west.comm.exception.MyException;
import com.west.comm.util.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author west
 * @date 2021/3/5 9:38
 */
@RequiredArgsConstructor
public class UserMobileAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final RedisUtils redisUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取过滤器封装的token信息
        UserMobileAuthenticationToken authenticationToken = (UserMobileAuthenticationToken) authentication;

        String mobile = (String) authenticationToken.getPrincipal();
        String mobileCode = (String) authenticationToken.getCredentials();

        // 查询验证码
        String code = (String) redisUtils.get(mobile);
        // 清除验证码
        redisUtils.del(mobile);
        if (StrUtil.isBlank(code)) {
            throw new MyException("验证码不存在或已过期");
        }
        if (StrUtil.isBlank(mobileCode) || !mobileCode.equalsIgnoreCase(code)) {
            throw new MyException("验证码错误");
        }

        AuthUser user = (AuthUser) userDetailsService.loadUserByUsername(mobile);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        if (!user.isEnabled()) {
            throw new MyException("账号未激活！");
        }
        //通过
        UserMobileAuthenticationToken authenticationResult = new UserMobileAuthenticationToken(user, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserMobileAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
