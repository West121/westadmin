package com.west.sys.controller;

import cn.hutool.core.util.RandomUtil;
import com.west.comm.domain.R;
import com.west.log.annotation.SysLog;
import com.west.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 api
 *
 * @author west
 * @date 2021/2/7 17:14
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService userService;

    /**
     * 根据用户 id 获取用户信息
     *
     * @param id 用户主键 id
     * @return 用户信息
     */
    @SysLog("测试")
    @GetMapping("/user/{id}")
    public R getSysUserInfoById(@PathVariable Long id) {
//        int i = 1 / 0;
        System.out.println("1.当前线程ID：" + Thread.currentThread().getId());
        try {
            Thread.sleep(RandomUtil.randomLong(500L, 3000L));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok(id);
    }
}
