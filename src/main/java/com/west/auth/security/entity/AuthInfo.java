package com.west.auth.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author west
 * @date 2021/3/5 11:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 5914297985732441911L;

    private String token;
    private AuthUser authUser;
}
