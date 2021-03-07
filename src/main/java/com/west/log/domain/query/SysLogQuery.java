package com.west.log.domain.query;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统日志查询
 *
 * @author west
 * @date 2021/2/9 11:14
 */
@Data
public class SysLogQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = -5733709914693026742L;

    private String logType;

    private List<LocalDateTime> createTime;
}
