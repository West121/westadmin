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
 * 角色菜单关联
 *
 * @author west
 * @date 2021/2/7 16:44
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenu implements Serializable {

    @Serial
    private static final long serialVersionUID = -9128114127853002883L;

    /**
     * 角色 id
     */
    @TableId("role_id")
    private Long roleId;

    /**
     * 菜单 id
     */
    @TableField("menu_id")
    private Long menuId;
}
