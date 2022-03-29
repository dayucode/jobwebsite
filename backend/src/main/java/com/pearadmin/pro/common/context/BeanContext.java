package com.pearadmin.pro.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Bean Context
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Component
public class BeanContext  implements ApplicationContextAware {

    /**
     * Ioc Context
     * */
    public static ApplicationContext applicationContext;

    /**
     * 设置 Spring 上下文
     * */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanContext.applicationContext = applicationContext;
    }

    /**
     * 获取 Bean 实体
     *
     * @param name 名称
     * */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 获取 Bean 实例
     *
     * @param name 名称
     * @param clazz 类型
     * */
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    /**
     * 获取 Bean 实例
     *
     * @param clazz 类型
     * */
    public static<T> T getBean( Class<T> clazz){ return applicationContext.getBean(clazz); }

    /**
     * Bean 是否存在
     *
     * @param name 名称
     * */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * Bean 是否为单例
     *
     * @param name 名称
     * */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * 获取 Bean 类型
     *
     * @param name 名称
     * */
    public static Class<? extends Object> getType(String name) {
        return applicationContext.getType(name);
    }

}
