package com.west.auth.oauth2.context;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * @author west
 * @date 2021/3/4 10:14
 */
public final class OAuth2Context {
    private OAuth2Context() {
    }

    public static String getPrincipalName() {
        return getOAuth2AuthenticationToken().getName();
    }

    public static String getClientRegistrationId() {
        return getOAuth2AuthenticationToken().getAuthorizedClientRegistrationId();
    }

    public static OAuth2User getOAuth2User() {
        return getOAuth2AuthenticationToken().getPrincipal();
    }

    public static OAuth2AuthenticationToken getOAuth2AuthenticationToken() {
        return (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }

}
