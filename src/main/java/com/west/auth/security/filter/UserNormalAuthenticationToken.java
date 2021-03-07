package com.west.auth.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;

/**
 * @author west
 * @date 2021/3/5 9:43
 */
@Slf4j
public class UserNormalAuthenticationToken extends AbstractAuthenticationToken {

    @Serial
    private static final long serialVersionUID = 2124269082693092334L;

    private final Object principal;

    private Object credentials;

    public UserNormalAuthenticationToken(Object principal, Object credentials) {
        super((Collection) null);
        this.principal = principal;
        this.credentials = credentials;
        // 是否已经认证，false  , 在后续provider 中认证通过设置为 true
        this.setAuthenticated(false);
        log.info("UserLoginNormalAuthenticationToken setAuthenticated ->false loading ...");
    }

    /**
     * 当 token 在 provider 中通过验证，调用该构造函数创建新的 token
     *
     * @param principal   用户信息 {@link UserDetails}
     * @param authorities 用户的权限集合
     */
    public UserNormalAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        // 是否已经认证，true
        super.setAuthenticated(true);
        log.info("VanasUserAuthenticationToken setAuthenticated ->true loading ...");
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
