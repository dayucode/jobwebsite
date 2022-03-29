package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.param.SysRoleGiveRequest;
import com.pearadmin.pro.modules.sys.param.SysRoleRequest;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    /**
     * 获取角色部门
     *
     * @param roleId 用户编号
     *
     * @return {@link SysDept}
     * */
    List<SysDept> dept(String roleId);

    /**
     * 获取角色列表
     *
     * @param request 查询参数
     *
     * @return {@link SysRole}
     * */
    List<SysRole> list(SysRoleRequest request);

    /**
     * 获取角色列表
     *
     * @param request 查询参数
     *
     * @return {@link SysRole}
     * */
    PageInfo<SysRole> page(SysRoleRequest request);

    /**
     * 分配权限
     *
     * @param request 请求参数
     *
     * @return {@link Boolean}
     * */
    Boolean give(SysRoleGiveRequest request);

    /**
     * 角色权限
     *
     * @param roleId 角色编号
     * */
    List<SysPower> getRolePower(String roleId);

    /**
     * Describe: 根据 id 修改用户数据
     * Param: ids
     * Return: 操作结果
     * */
    boolean update(SysRole sysRole);
    /**
     * 部门权限
     *
     * @param roleId 角色编号
     * */
    List<SysDept> getRoleDept(String roleId);
}
