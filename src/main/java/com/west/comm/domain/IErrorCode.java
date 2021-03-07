package com.west.comm.domain;

/**
 * 错误码接口
 *
 * @author west
 * @date 2021/2/7 16:24
 */
public interface IErrorCode {

    /**
     * 响应码
     *
     * @return
     */
    long getCode();

    /**
     * 错误描述
     *
     * @return
     */
    String getMsg();
}
