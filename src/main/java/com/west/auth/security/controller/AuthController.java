package com.west.auth.security.controller;

import com.west.auth.anno.AnonymousGet;
import com.west.auth.anno.AnonymousPost;
import com.west.auth.security.entity.LoginUser;
import com.west.auth.security.service.AuthService;
import com.west.auth.security.service.OnlineService;
import com.west.comm.domain.R;
import com.west.auth.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 登陆退出、认证授权 api
 *
 * @author west
 * @date 2021/3/1 17:55
 */
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final OnlineService onlineService;

    /**
     * 获取验证码
     *
     * @return 验证码
     */
    @AnonymousGet("/auth/code")
    public R getCode() {
        return R.ok(authService.getCaptchaCode());
    }

    /**
     * 获取短信验证码
     *
     * @return 短信验证码
     */
    @AnonymousGet("/auth/smsCode")
    public R getSmsCode(@RequestParam("mobile") String mobile) {
        authService.getSmsCode(mobile);
        return R.ok();
    }

    /**
     * 登陆
     *
     * @param loginUser 登陆账号信息
     * @param request   request
     * @return token
     */
    @AnonymousPost("/auth/login")
    public R login(@Validated @RequestBody LoginUser loginUser, HttpServletRequest request) throws Exception {
        return R.ok(authService.login(loginUser, request));
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @GetMapping("/auth/userInfo")
    public R getUserInfo() {
        return R.ok(SecurityUtils.getCurrentUser());
    }

    /**
     * 退出登陆
     *
     * @return 成功或失败
     */
    @PostMapping("/auth/logout")
    public R logout(HttpServletRequest request) {
        return R.ok();
    }
}
