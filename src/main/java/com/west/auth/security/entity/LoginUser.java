package com.west.auth.security.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

/**
 * 登陆用户
 *
 * @author west
 * @date 2021/3/1 17:58
 */
@Data
public class LoginUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 680795757236768941L;


    /**
     * uuid
     */
    private String uuid;

    /**
     * 账号
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String code;

    /**
     * 登陆方式
     */
    private String loginType;
}
