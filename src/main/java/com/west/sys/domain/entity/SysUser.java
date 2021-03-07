package com.west.sys.domain.entity;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统用户
 *
 * @author west
 * @date 2021/2/7 16:44
 */
@Data
@TableName("sys_user")
public class SysUser implements Serializable {

    @Serial
    private static final long serialVersionUID = -5097794632270084043L;

    /**
     * 用户 id
     */
    @TableId("user_id")
    private Long userId;

    /**
     * 账号
     */
    @TableField("username")
    private String username;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 联系方式
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 出生日期
     */
    @TableField("birth")
    private String birth;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 删除标记，0：未删除，1：已删除
     */
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 部门 id
     */
    @TableField("dept_id")
    private Long deptId;

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

    /**
     * 查询用户是否有效
     *
     * @return
     */
    public boolean getEnabled() {
        return ObjectUtil.isNotNull(this.delFlag) && 0 == this.delFlag;
    }
}
