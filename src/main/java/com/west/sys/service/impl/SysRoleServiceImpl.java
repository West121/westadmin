package com.west.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.west.sys.domain.entity.SysRole;
import com.west.sys.mapper.SysRoleMapper;
import com.west.sys.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * 系统角色 service 实现
 *
 * @author west
 * @date 2021-03-08 11:20
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService {}
