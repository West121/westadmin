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
 * 系统字典
 *
 * @author west
 * @date 2021/2/7 16:44
 */
@Data
@TableName("sys_dict")
public class SysDict implements Serializable {

    @Serial
    private static final long serialVersionUID = -6984889570305753976L;

    /**
     * 字典 id
     */
    @TableId("dict_id")
    private Long dictId;

    /**
     * 字典名称
     */
    @TableField("dict_name")
    private String dictName;

    /**
     * 字典描述
     */
    @TableField("dict_desc")
    private String dictDesc;

    /**
     * 是否是系统内置，0：否，1：是
     */
    @TableField("`sys`")
    private Integer sys;

    /**
     * 备注信息
     */
    @TableField("remark")
    private String remark;

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
    @TableField(value = " ", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
