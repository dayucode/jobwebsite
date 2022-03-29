package com.pearadmin.pro.modules.sys.param;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 角色授权 -- 参数实体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 */
@Data
@Alias("SysRoleAuthRequest")
public class SysRoleAuthRequest{

    /**
     * 角色编号
     */
    private String roleId;
    /**
     * 权限标识
     */
    private String powerIds;
    /**
     * 部门标识
     */
    private String deptIds;
    /**
     * 部门范围标识
     */
    private String scope;
}
