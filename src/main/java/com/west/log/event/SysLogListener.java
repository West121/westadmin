package com.west.log.event;

import com.west.log.domain.entity.SysLog;
import com.west.log.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 系统日志异步监听
 *
 * @author west
 * @date 2021/2/19 9:45
 */
@Component
@RequiredArgsConstructor
public class SysLogListener {

    private final SysLogService sysLogService;

    @Async
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        System.out.println("4.当前线程ID：" + Thread.currentThread().getId());
        SysLog sysLog = (SysLog) event.getSource();
        sysLogService.save(sysLog);
    }
}
