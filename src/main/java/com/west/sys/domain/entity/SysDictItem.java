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
 * 系统字典项
 *
 * @author west
 * @date 2021/2/7 16:44
 */
@Data
@TableName("sys_dict_item")
public class SysDictItem implements Serializable {

    @Serial
    private static final long serialVersionUID = -1637206331799407207L;

    /**
     * 字典项 id
     */
    @TableId("dict_item_id")
    private Long dictItemId;

    /**
     * 标签名
     */
    @TableField("label")
    private String label;

    /**
     * 数据值
     */
    @TableField("value")
    private String value;

    /**
     * 描述
     */
    @TableField("dict_item_desc")
    private String dictItemDesc;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 排序
     */
    @TableField("dict_item_sort")
    private Integer dictItemSort;

    /**
     * 所属字典 id
     */
    @TableField("dict_id")
    private Long dictId;

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
    @TableField(value = "ang", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
