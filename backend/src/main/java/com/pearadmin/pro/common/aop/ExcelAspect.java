package com.pearadmin.pro.common.aop;

import com.pearadmin.pro.common.aop.annotation.Excel;
import com.pearadmin.pro.common.aop.enums.Model;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel 导出 Aop
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Aspect
@Component
public class ExcelAspect {

    /**
     * 切 面 编 程
     * */
    @Pointcut("@annotation(com.pearadmin.pro.common.aop.annotation.Excel) || @within(com.pearadmin.pro.common.aop.annotation.Excel)")
    public void dsPointCut() { }

    /**
     * 处 理 系 统 日 志
     * */
    @Around("dsPointCut()")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Object data = null;

        Excel annotation = getAnnotation(joinPoint);
        Class clazz = annotation.clazz();
        Model model = annotation.model();
        HttpServletResponse response = ServletUtil.getResponse();

        try {

            if(model.equals(Model.READ)) {

                // TODO Excel 导入
            }

            data = joinPoint.proceed();

            //if(model.equals(Model.WRITE)) ExcelUtil.write(response, clazz, toList(data, clazz));

        }catch (Exception e){
            // 堆 栈 信 息
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 获 取 注 解
     * */
    public Excel getAnnotation(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        Excel targetExcel = targetClass.getAnnotation(Excel.class);
        if ( targetExcel != null) {
            return targetExcel;
        } else {
            Method method = signature.getMethod();
            Excel excel = method.getAnnotation(Excel.class);
            return excel;
        }
    }

    /**
     * List 转换
     * */
    public static <T> List<T> toList(Object obj, Class<T> clazz)
    {
        List<T> result =new ArrayList<T>();
        if(obj instanceof List<?>)
        {
            for (Object o : (List<?>) obj)
            {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}
