package com.pearadmin.pro.common.web.interceptor.enums;

/**
 * Describe: 接口权限限制
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public enum Scope {

    /**
     * 全部
     * */
    ALL,

    /**
     * 自己
     * */
    SELF,

    /**
     * 部门
     * */
    DEPT,

    /**
     * 部门 及其 子部门
     * */
    DEPT_CHILD,

    /**
     * 自动
     * */
    AUTO,

    /**
     * 自定义
     * */
    CUSTOM

}
