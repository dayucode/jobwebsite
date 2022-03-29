package com.pearadmin.pro.common.aop;

import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.aop.enums.Action;
import com.pearadmin.pro.common.context.BaseContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * Log 实现 Aop
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Aspect
@Component
public class LogAspect {

    /**
     * 基 础 上 下 文
     * */
    @Resource
    private BaseContext context;

    /**
     * 切 面 编 程
     * */
    @Pointcut("@annotation(com.pearadmin.pro.common.aop.annotation.Log) || @within(com.pearadmin.pro.common.aop.annotation.Log)")
    public void dsPointCut() { }

    /**
     * 处 理 系 统 日 志
     * */
    @Around("dsPointCut()")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Object result = null;

        // 注解解析
        Log annotation = getAnnotation(joinPoint);
        String title = annotation.title();
        Action action = annotation.action();
        String describe = annotation.describe();

        try {
            // 执 行 方 法
            result = joinPoint.proceed();
            // 记 录 日 志
            context.record(title, describe, action, true, null,null);

        }catch (Exception e){

            // 堆 栈 信 息
            e.printStackTrace();
            // 异 常 记 录
            context.record(title, describe, action, false, null, null);
            // 向上抛出
            throw e;
        }
        return result;
    }

    /**
     * 获 取 注 解
     * */
    public Log getAnnotation(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        Log targetLog = targetClass.getAnnotation(Log.class);
        if ( targetLog != null) {
            return targetLog;
        } else {
            Method method = signature.getMethod();
            Log log = method.getAnnotation(Log.class);
            return log;
        }
    }
}