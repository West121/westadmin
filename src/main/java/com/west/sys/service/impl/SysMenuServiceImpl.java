package com.west.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.west.sys.domain.entity.SysMenu;
import com.west.sys.mapper.SysMenuMapper;
import com.west.sys.service.SysMenuService;
import org.springframework.stereotype.Service;

/**
 * 系统菜单 service 实现
 *
 * @author west
 * @date 2021-03-08 11:21
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService {}
