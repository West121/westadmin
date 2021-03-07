package com.west.auth.security.filter;

import com.west.auth.security.entity.AuthUser;
import com.west.comm.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author west
 * @date 2021/3/5 9:38
 */
@RequiredArgsConstructor
public class UserNormalAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取过滤器封装的token信息
        UserNormalAuthenticationToken authenticationToken = (UserNormalAuthenticationToken) authentication;

        String username = (String) authenticationToken.getPrincipal();
        AuthUser user = (AuthUser) userDetailsService.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        if (!user.isEnabled()) {
            throw new MyException("账号未激活！");
        }
        // 检验密码
        String inputPassword = authenticationToken.getCredentials().toString();
        if (!passwordEncoder.matches(inputPassword, user.getPassword())) {
            throw new MyException("密码错误");
        }

        //通过
        UserNormalAuthenticationToken authenticationResult = new UserNormalAuthenticationToken(user, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserNormalAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
