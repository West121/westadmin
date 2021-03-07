package com.west.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.west.sys.domain.dto.SysUserDTO;
import com.west.sys.domain.entity.SysUser;

/**
 * 用户 service
 *
 * @author west
 * @date 2021/2/7 17:12
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户DTO
     */
    SysUserDTO getByUsername(String username);
}
