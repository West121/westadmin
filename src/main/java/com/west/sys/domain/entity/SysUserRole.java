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
 * 用户角色关联
 *
 * @author west
 * @date 2021/2/7 16:44
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {

    @Serial
    private static final long serialVersionUID = -8498064294711919728L;

    /**
     * 用户 id
     */
    @TableId("user_id")
    private Long userId;

    /**
     * 角色 id
     */
    @TableField("role_id")
    private Long roleId;
}
