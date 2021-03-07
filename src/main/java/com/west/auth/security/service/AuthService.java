package com.west.auth.security.service;

import com.west.auth.security.entity.AuthInfo;
import com.west.auth.security.entity.CaptchaCode;
import com.west.auth.security.entity.LoginUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author west
 * @date 2021/3/5 11:17
 */
public interface AuthService {


    CaptchaCode getCaptchaCode();

    void getSmsCode(String mobile);

    AuthInfo login(LoginUser loginUser, HttpServletRequest request) throws Exception;
}
