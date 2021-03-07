package com.west.sys.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统岗位
 *
 * @author west
 * @date 2021/2/7 16:44
 */
@Data
@TableName("sys_job")
public class SysJob implements Serializable {

    @Serial
    private static final long serialVersionUID = -4043350425543920158L;

    /**
     * 岗位 id
     */
    @TableId("job_id")
    private Long jobId;

    /**
     * 名称
     */
    @TableField("job_name")
    private String jobName;

    /**
     * 排序
     */
    @TableField("job_sort")
    private Integer jobSort;

    /**
     * 删除标记，0：未删除，1：已删除
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
