package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * 用户角色模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
@Accessors(chain = true)
@Alias("SysUserRole")
@TableName("sys_user_role")
public class SysUserRole {

    /**
     * 编号
     * */
    @TableId("id")
    private String id;

    /**
     * 用户编号
     * */
    @TableField("user_id")
    private String userId;

    /**
     * 角色编号
     * */
    @TableField("role_id")
    private String roleId;

    /**
     * 租户编号
     * */
    @TableField("tenant_id")
    private String tenantId;

}
