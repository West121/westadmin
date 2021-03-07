package com.west.auth.security.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.west.auth.security.entity.*;
import com.west.auth.security.filter.JwtTokenProvider;
import com.west.auth.security.filter.UserMobileAuthenticationToken;
import com.west.auth.security.filter.UserNormalAuthenticationToken;
import com.west.auth.security.properties.LoginProperties;
import com.west.auth.security.properties.SecurityProperties;
import com.west.auth.security.service.AuthService;
import com.west.auth.security.service.OnlineService;
import com.west.comm.config.RsaProperties;
import com.west.comm.exception.MyException;
import com.west.comm.util.RedisUtils;
import com.west.comm.util.RsaUtils;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author west
 * @date 2021/3/5 11:17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final String SPRING_SECURITY_FORM_MOBILE_KEY = "mobile";

    private final LoginProperties loginProperties;
    private final SecurityProperties securityProperties;
    private final RedisUtils redisUtils;
    private final OnlineService onlineService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public CaptchaCode getCaptchaCode() {
        // 获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = securityProperties.getCodeKey() + IdUtil.simpleUUID();
        log.info(uuid);
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }

        // 保存
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);

        // 返回
        return new CaptchaCode(captcha.toBase64(), uuid);
    }

    @Override
    public void getSmsCode(String mobile) {
        // 生成短信验证码
        String code = RandomUtil.randomNumbers(6);
        log.info(code);
        // todo 发送短信验证码
        // todo 配置短信验证码过期时间
        // 保存短信验证码
        redisUtils.set(mobile, code, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
    }

    @Override
    public AuthInfo login(LoginUser loginUser, HttpServletRequest request) throws Exception {
        // 登陆方式
        String loginType = loginUser.getLoginType();
        Authentication authentication;
        // 短信或其它
        if (loginType.equalsIgnoreCase(SPRING_SECURITY_FORM_MOBILE_KEY)) {
            UserMobileAuthenticationToken authenticationToken = new UserMobileAuthenticationToken(loginUser.getUsername(), loginUser.getCode());
            authentication = authenticationManager.authenticate(authenticationToken);
        } else {
            // 密码解密
            String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, loginUser.getPassword());
            // 查询验证码
            String code = (String) redisUtils.get(loginUser.getUuid());
            // 清除验证码
            redisUtils.del(loginUser.getUuid());
            if (StrUtil.isBlank(code)) {
                throw new MyException("验证码不存在或已过期");
            }
            if (StrUtil.isBlank(loginUser.getCode()) || !loginUser.getCode().equalsIgnoreCase(code)) {
                throw new MyException("验证码错误");
            }
            UserNormalAuthenticationToken authenticationToken = new UserNormalAuthenticationToken(loginUser.getUsername(), password);
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.createToken(authentication);
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        // 保存在线信息
        onlineService.save(authUser, token, request);
        if (loginProperties.isSingleLogin()) {
            //踢掉之前已经登录的token
            onlineService.checkLoginOnUser(loginUser.getUsername(), token);
        }
        // 返回 token 与 用户信息
        return new AuthInfo(securityProperties.getTokenStartWith() + token, authUser);
    }
}
