package com.west.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.west.sys.domain.entity.SysDept;
import com.west.sys.mapper.SysDeptMapper;
import com.west.sys.service.SysDeptService;
import org.springframework.stereotype.Service;

/**
 * 系统部门 service 实现
 *
 * @author west
 * @date 2021-03-08 11:18
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept>
    implements SysDeptService {}
