package com.west.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.west.sys.domain.dto.SysUserDTO;
import com.west.sys.domain.entity.SysUser;
import com.west.sys.mapper.SysUserMapper;
import com.west.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户 service 实现
 *
 * @author west
 * @date 2021/2/7 17:12
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUserDTO getByUsername(String username) {
        return baseMapper.getDtoByUsername(username);
    }
}
