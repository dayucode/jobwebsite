package com.pearadmin.pro.common.constant;

/**
 * Tenant 多租户静态配置
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 */
public class TenantConstant {

    /**
     * 开启多租户
     * */
    public static boolean enable = false;

    /**
     * 租户字段
     * */
    public static String TENANT_COLUMN = "tenant_id";

    /**
     * 过滤内容
     * */
    public static String IGNORE_TABLE[] = {
            "sys_config",
            "sys_dict_data",
            "sys_dict",
            "sys_tenant",
            "sys_power",
            "sys_job",
            "sys_job_log",
            "sys_data_source",
    };

    /**
     * 创建租户时, 默认角色 name 与 code
     * */
    public static String DEFAULT_ROLE_NAME = "默认角色";

    public static String DEFAULT_ROLE_CODE = "default";

    /**
     * 创建租户时, 默认岗位 name 与 code
     * */
    public static String DEFAULT_POST_NAME = "默认岗位";

    public static String DEFAULT_POST_CODE = "default";

    /**
     * 创建租户时, 默认部门 name 与 address
     * */
    public static String DEFAULT_DEPT_NAME = "顶级部门";

    public static String DEFAULT_DEPT_ADDRESS = "详细地址";

    public static String DEFAULT_DEPT_PARENT = "0";

}
