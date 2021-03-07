package com.west.auth.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author west
 * @date 2021/3/5 11:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaCode implements Serializable {

    @Serial
    private static final long serialVersionUID = -7247707893885999665L;

    private String img;
    private String uuid;
}
