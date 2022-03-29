package com.pearadmin.pro.common.web.interceptor.annotation;

import java.lang.annotation.*;

/**
 * 忽略多租户
 *
 * Author: 就眠仪式
 * CreateTime: 2021/04/01
 * */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TenantIgnore {

}
