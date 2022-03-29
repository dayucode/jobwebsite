package com.pearadmin.pro.common.aop.annotation;

import com.pearadmin.pro.common.aop.enums.Action;

import java.lang.annotation.*;

/**
 * 日志 注解
 *
 * Author 就 眠 仪 式
 * CreateTime: 2021/04/20
 * */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Log {

    /**
     * Title 默认输入
     * */
    String title() default "暂无标题";

    /**
     * Describe 默认输入
     * */
    String describe() default "暂无描述";

    /**
     * Action 操作类型
     * */
    Action action() default Action.QUERY;

}
