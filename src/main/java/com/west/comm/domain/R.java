package com.west.comm.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

/**
 * 响应
 *
 * @author west
 * @date 2021/2/7 16:22
 */
@Data
@Accessors(chain = true)
public class R<T> implements Serializable {

    /**
     * serialVersionUID
     */
    @Serial
    private static final long serialVersionUID = -1941296503990246767L;

    /**
     * 业务错误码
     */
    private long code;

    /**
     * 描述
     */
    private String msg;

    /**
     * 结果集
     */
    private T data;

    public R() {
    }

    public R(IErrorCode errorCode) {
        errorCode = Optional.ofNullable(errorCode).orElse(ErrorCode.FAILED);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static <T> R ok() {
        ErrorCode aec = ErrorCode.SUCCESS;
        return restResult(null, aec);
    }

    public static <T> R<T> ok(T data) {
        ErrorCode aec = ErrorCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            aec = ErrorCode.FAILED;
        }
        return restResult(data, aec);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, ErrorCode.FAILED.getCode(), msg);
    }

    public static <T> R<T> failed(IErrorCode errorCode) {
        return restResult(null, errorCode);
    }

    public static <T> R<T> restResult(T data, IErrorCode errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg());
    }

    private static <T> R<T> restResult(T data, long code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
