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
 * 系统菜单
 *
 * @author west
 * @date 2021/2/7 16:44
 */
@Data
@TableName("sys_menu")
public class SysMenu implements Serializable {

    @Serial
    private static final long serialVersionUID = -4397355083268772956L;

    /**
     * 菜单 id
     */
    @TableId("menu_id")
    private Long menuId;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 菜单图标
     */
    @TableField("menu_icon")
    private String menuIcon;

    /**
     * 前端路由地址
     */
    @TableField("menu_path")
    private String menuPath;

    /**
     * 菜单类型，目录、菜单、按钮
     */
    @TableField("menu_type")
    private String menuType;

    /**
     * 菜单排序
     */
    @TableField("menu_sort")
    private Integer menuSort;

    /**
     * 权限标识
     */
    @TableField("permission")
    private String permission;

    /**
     * 父节点 id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 是否缓存，0：否，1：是
     */
    @TableField("cache")
    private Integer cache;


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
    @TableField(value = "ao ", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
