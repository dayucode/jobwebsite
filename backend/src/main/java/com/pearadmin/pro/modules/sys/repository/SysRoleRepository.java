package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.common.web.interceptor.annotation.TenantIgnore;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.param.SysRoleRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleRepository extends BaseMapper<SysRole> {

    /**
     * 获取用户角色
     *
     * @param userId 用户编号
     *
     * @return {@link SysRole}
     * */
    @TenantIgnore
    List<SysRole> selectRoleByUserId(String userId);

    /**
     * 获取用户列表
     *
     * @param request 查询参数
     *
     * @return {@link SysRole}
     * */
    List<SysRole> selectRole(@Param("request")SysRoleRequest request);
    /**
     * Describe: 根据 Id 修改角色
     * Param: SysRole
     * Return: Integer
     * */
    int updateById(SysRole sysRole);
}
