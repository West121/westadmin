package com.west.log.event;

import com.west.log.domain.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志 event
 *
 * @author west
 * @date 2021/2/19 9:44
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(SysLog source) {
        super(source);
    }
}
