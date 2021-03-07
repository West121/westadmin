package com.west.auth.security.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.west.auth.security.entity.OnlineUser;
import com.west.auth.security.properties.SecurityProperties;
import com.west.auth.security.service.OnlineService;
import com.west.auth.security.util.OnlineUserCacheUtils;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author west
 * @date 2021/3/4 16:48
 */
@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final SecurityProperties properties;
    private final OnlineService onlineService;
    private final OnlineUserCacheUtils onlineUserCacheUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = getJwtFromRequest(request);

        if (StrUtil.isNotBlank(jwtToken)) {
            OnlineUser onlineUser = null;
            boolean cleanUserCache = false;
            try {
                onlineUser = onlineService.getByKey(properties.getOnlineKey() + jwtToken);
            } catch (ExpiredJwtException e) {
                log.error(e.getMessage());
                cleanUserCache = true;
            } finally {
                if (cleanUserCache || ObjectUtil.isNull(onlineUser)) {
                    onlineUserCacheUtils.cleanUserCache(String.valueOf(jwtTokenProvider.getClaims(jwtToken).get(JwtTokenProvider.AUTHORITIES_KEY)));
                }
            }
            if (ObjectUtil.isNotNull(onlineUser)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(jwtToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // Token 续期
                jwtTokenProvider.checkRenewal(jwtToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(properties.getHeader());
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith(properties.getTokenStartWith())) {
            return bearerToken.replace(properties.getTokenStartWith(), "");
        } else {
            log.debug("非法Token：{}", bearerToken);
        }
        return null;
    }
}
