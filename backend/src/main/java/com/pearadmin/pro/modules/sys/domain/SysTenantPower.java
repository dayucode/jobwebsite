package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 角色权限模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
@Alias("SysTenantPower")
@TableName("sys_tenant_power")
public class SysTenantPower {

    /**
     * 编号
     * */
    private String id;

    /**
     * 租户编号
     * */
    private String tenantId;

    /**
     * 权限编号
     * */
    private String powerId;

}
