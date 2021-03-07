package com.west.log.controller;

import com.west.comm.domain.R;
import com.west.log.domain.query.SysLogQuery;
import com.west.log.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统日志 api
 *
 * @author west
 * @date 2021/2/9 11:11
 */
@RestController
@RequiredArgsConstructor
public class SysLogController {

    private final SysLogService logService;

    /**
     * 查询日志列表
     *
     * @param query 查询条件
     * @param page  分页
     * @return 日志分页列表
     */
    @GetMapping("/log/list")
    public R getAll(@RequestBody SysLogQuery query, Pageable page) {
        return R.ok();
    }
}
