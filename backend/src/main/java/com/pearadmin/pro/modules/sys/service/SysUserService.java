package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.modules.sys.domain.*;
import com.pearadmin.pro.modules.sys.param.SysUserRequest;

import java.util.List;

/**
 * 用户服务
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
public interface SysUserService extends IService<SysUser> {

    /**
     * 获取用户角色
     *
     * @param userId 用户编号
     *
     * @return {@link SysRole}
     * */
    List<SysRole> role(String userId);

    /**
     * 获取用户列表
     *
     * @param request 参数实体
     *
     * @return {@link SysUser}
     * */
    List<SysUser> list(SysUserRequest request);

    /**
     * 获取用户列表 (分页)
     *
     * @param request 查询参数
     *
     * @return {@link SysUser}
     * */
    PageInfo<SysUser> page(SysUserRequest request);

    /**
     * 获取用户菜单
     *
     * @param userId 用户编号
     *
     * @return {@link SysUser}
     * */
    List<SysMenu> menu(String userId);

    /**
     * 获取用户权限
     *
     * @param userId 用户编号
     *
     * @return {@link SysPower}
     */
    List<SysPower> power(String userId);

    /**
     * 分配用户角色
     *
     * @param userId 用户编号
     * @param roleIds 角色编号
     *
     * @return {@link Boolean}
     * */
    Boolean give(String userId, List<String> roleIds);

    /**
     * 获取用户部门 (数据权限)
     *
     * @param userId 用户编号
     *
     * @return {@link SysDept}
     * */
    List<SysDept> dept(String userId);

    /**
     * 检测用户存在
     *
     * @param username 用户名
     *
     * @return {@link Boolean}
     * */
    Boolean exist(String username);

    /**
     * 修改密码
     *
     * @param userId 用户编号
     * @param password 密码实体
     *
     * @return {@link Boolean}
     * */
    Boolean editPassword(String userId, String password);

    /**
     * 重置密码
     *
     * @param userId 用户编号
     *
     * @return {@link Boolean}
     * */
    Boolean resetPassword(String userId);

    /**
     * 密码比较
     *
     * @param userId 用户编号
     * @param password 密码
     *
     * @return {@link Boolean}
     * */
    Boolean contrastPassword(String userId, String password);

    /**
     * Describe: 修改用户状态
     * Param: SysPower
     * Return: 操作结果
     * */
    boolean update(SysUser sysUser);

    /**
     * Describe: 获取用户角色数据
     * Param: SysUser
     * Return: 操作结果
     * */
    List<SysRole> getUserRole(String userId);

    /**
     * 游客注册为普通用户
     * @param register
     * @return
     */
    Result register(SysUser sysUser);
}
