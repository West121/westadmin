package com.west.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.west.sys.domain.entity.SysJob;
import com.west.sys.mapper.SysJobMapper;
import com.west.sys.service.SysJobService;
import org.springframework.stereotype.Service;

/**
 * 系统岗位 service 实现
 *
 * @author west
 * @date 2021-03-08 11:20
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {}
