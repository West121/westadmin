package com.west.log.service;

import com.west.log.domain.entity.SysLog;
import com.west.log.domain.query.SysLogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 系统日志 service
 *
 * @author west
 * @date 2021/2/9 11:10
 */
public interface SysLogService {

    /**
     * 查询日志列表
     *
     * @param query 查询条件
     * @param page  分页
     * @return 日志列表
     */
    Page<SysLog> list(SysLogQuery query, Pageable page);

    /**
     * 保存日志
     *
     * @param sysLog 日志
     */
    void save(SysLog sysLog);
}
