package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * 角色部门模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */

@Data
@Accessors(chain = true)
@Alias("SysRoleDept")
@TableName("sys_role_dept")
public class SysRoleDept{

    /**
     * 编号
     */
    @TableId("id")
    private String id;

    /**
     * 角色编号
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 部门编号
     */
    @TableField("dept_id")
    private String deptId;

    /**
     * 租户编号
     * */
    @TableField("tenant_id")
    private String tenantId;

}
