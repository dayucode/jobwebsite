package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.common.web.interceptor.annotation.TenantIgnore;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.param.SysUserRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SysUserRepository extends BaseMapper<SysUser>{

    /**
     * 获取用户列表
     *
     * @param request 查询参数
     *
     * @return {@link SysUser}
     * */
    List<SysUser> selectUser(@Param("request") SysUserRequest request);

    /**
     * 根据 username 获取用户
     *
     * @param username 用户名称
     *
     * @return {@link SysUser}
     * */
    @TenantIgnore
    SysUser selectByUsername(@Param("username") String username);
}
