package com.west.log.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.west.comm.util.HttpUtils;
import com.west.log.domain.entity.SysLog;
import com.west.log.domain.enums.LogTypeEnum;
import com.west.log.domain.query.SysLogQuery;
import com.west.log.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统日志 service 实现
 *
 * @author west
 * @date 2021/2/9 11:11
 */
@Service
@RequiredArgsConstructor
public class SysLogServiceImpl implements SysLogService {

    private final MongoTemplate template;

    @Override
    public Page<SysLog> list(SysLogQuery query, Pageable page) {
        // mongoDB 查询
        Query mongoQuery = new Query();

        if (ObjectUtil.isNotNull(query)) {
            if (StrUtil.isNotBlank(query.getLogType())) {
                mongoQuery.addCriteria(Criteria.where("logType").is(query.getLogType()));
            }
            if (CollUtil.isNotEmpty(query.getCreateTime())) {
                mongoQuery.addCriteria(Criteria.where("createTime").gte(query.getCreateTime().get(0))
                        .andOperator(Criteria.where("createTime").lte(query.getCreateTime().get(0))));
            }
        }
        // 分页查询
        List<SysLog> list = template.find(mongoQuery.with(page), SysLog.class);
        // 总数
        long count = template.count(mongoQuery, SysLog.class);
        // 结果
        Page<SysLog> result = new PageImpl(list, page, count);
        return result;
    }

    @Override
    public void save(SysLog sysLog) {
        System.out.println("5.当前线程ID：" + Thread.currentThread().getId());
        template.insert(sysLog);
    }
}
