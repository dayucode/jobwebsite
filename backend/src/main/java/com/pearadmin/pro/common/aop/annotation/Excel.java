package com.pearadmin.pro.common.aop.annotation;

import com.pearadmin.pro.common.aop.enums.Model;
import java.lang.annotation.*;

/**
 * Excel 注解
 *
 * Author 就 眠 仪 式
 * CreateTime: 2021/04/20
 * */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Excel {

    Class clazz();

    Model model() default Model.WRITE;

}
