package com.pearadmin.pro.modules.sys.param;

import lombok.Data;

import java.util.List;

/**
 * 租户授权列表 -- 参数实体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 */
@Data
public class SysTenantGiveRequest {

    /**
     * 租户编号
     * */
    private String tenantId;

    /**
     * 权限列表
     * */
    private List<String> powerIds;

}
