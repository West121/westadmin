package com.west.sys.domain.dto;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author west
 * @date 2021/3/4 15:37
 */
@Data
public class SysUserDTO {

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生日期
     */
    private String birth;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 删除标记，0：未删除，1：已删除
     */
    private Integer delFlag;

    /**
     * 部门 id
     */
    private Long deptId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 权限
     */
    private List<GrantedAuthority> authorities;

    /**
     * 查询用户是否有效
     *
     * @return
     */
    public boolean getEnabled() {
        return ObjectUtil.isNotNull(this.delFlag) && 0 == this.delFlag;
    }
}
