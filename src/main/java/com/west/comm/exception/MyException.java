package com.west.comm.exception;

import com.west.comm.domain.IErrorCode;

import java.io.Serial;

/**
 * 自定义异常
 *
 * @author west
 * @date 2021/2/7 16:23
 */
public class MyException extends RuntimeException {

    /**
     * serialVersionUID
     */
    @Serial
    private static final long serialVersionUID = -5334043790067308824L;

    public MyException(IErrorCode errorCode) {
        super(errorCode.getMsg());
    }

    public MyException(String errMsg) {
        super(errMsg);
    }
}
