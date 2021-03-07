package com.west.log.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统日志表
 *
 * @author west
 * @date 2021/2/9 10:49
 */
@Data
@Document(collection = "sys_log")
public class SysLog implements Serializable {

    @Serial
    private static final long serialVersionUID = -700054149762633725L;

    @Id
    private String logId;

    /**
     * 日志类型
     */
    private String logType;

    /**
     * 日志标题
     */
    private String title;

    /**
     * 请求ip
     */
    private String requestIp;

    /**
     * 地址
     */
    private String address;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 请求耗时
     */
    private Long time;

    /**
     * 操作方式
     */
    private String method;

    /**
     * 操作提交的数据
     */
    private String params;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
