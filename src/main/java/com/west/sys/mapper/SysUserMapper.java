package com.west.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.west.sys.domain.dto.SysUserDTO;
import com.west.sys.domain.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户 mapper
 *
 * @author west
 * @date 2021/2/7 17:04
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select " +
            "user_id,username,nickname," +
            "email,mobile,gender,birth," +
            "avatar,password,del_flag," +
            "dept_id,create_time,update_time " +
            "from sys_user " +
            "where username = #{username} " +
            "union all " +
            "select " +
            "user_id,username,nickname," +
            "email,mobile,gender,birth," +
            "avatar,password,del_flag," +
            "dept_id,create_time,update_time " +
            "from sys_user " +
            "where email = #{username} " +
            "union all " +
            "select " +
            "user_id,username,nickname," +
            "email,mobile,gender,birth," +
            "avatar,password,del_flag," +
            "dept_id,create_time,update_time " +
            "from sys_user " +
            "where mobile = #{username}")
    SysUserDTO getDtoByUsername(@Param("username") String username);
}
