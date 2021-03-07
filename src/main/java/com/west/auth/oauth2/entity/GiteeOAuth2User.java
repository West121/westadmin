package com.west.auth.oauth2.entity;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

/**
 * @author west
 * @date 2021/3/4 10:08
 */
@Data
public class GiteeOAuth2User implements OAuth2User {

    private String id;
    private String name;
    private String avatarUrl;
    private String[] privilege;

    @JsonIgnore
    private Set<GrantedAuthority> authorities = new HashSet<>();
    @JsonIgnore
    private Map<String, Object> attributes;
    @JsonIgnore
    private String nameAttributeKey;

    public static GiteeOAuth2User build(String json, String userNameAttributeName) {
        GiteeOAuth2User user = JSONUtil.toBean(json, GiteeOAuth2User.class);
        user.nameAttributeKey = userNameAttributeName;
        user.setAttributes();
        user.setAuthorities();

        return user;
    }

    private void setAttributes() {
        attributes = new HashMap<>();

        this.attributes.put("id", id);
        this.attributes.put("name", name);
        this.attributes.put("avatarUrl", avatarUrl);
    }

    private void setAuthorities() {
        authorities = new LinkedHashSet<>();
        for (String authority : privilege) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return this.getAttribute(this.nameAttributeKey).toString();
    }
}
