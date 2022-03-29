package com.pearadmin.pro.common.aop;

import com.alibaba.fastjson.JSON;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.common.aop.annotation.Repeat;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.ResultCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 重 复 提 交 拦 截 器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */
@Aspect
@Component
public class RepeatAspect extends HandlerInterceptorAdapter {

    @Resource
    private RedisTemplate<String, Map<String,Object>> redisTemplate;

    @Resource
    private UserContext userContext;

    /**
     * 参数
     * */
    public final String REPEAT_PARAMS = "repeatParams";

    /**
     * 时间
     * */
    public final String REPEAT_TIME = "repeatTime";

    /**
     * 数据
     * */
    public final String SESSION_REPEAT_KEY = "repeatData";

    /**
     * 间隔时间，单位:秒 默认10秒
     *
     * 两次相同参数的请求，如果间隔时间大于该参数，系统不会认定为重复提交的数据
     */
    private int intervalTime = 10;


    /**
     * 切 面 编 程
     * */
    @Pointcut("@annotation(com.pearadmin.pro.common.aop.annotation.Repeat) || @within(com.pearadmin.pro.common.aop.annotation.Repeat)")
    public void dsPointCut() { }


    /**
     * 设置，间隔时间
     * */
    public void setIntervalTime(int intervalTime)
    {
        this.intervalTime = intervalTime;
    }


    /**
     * 处 理 系 统 日 志
     * */
    @Around("dsPointCut()")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Object data = null;
        Repeat annotation = getAnnotation(joinPoint);
        HttpServletRequest request = ServletUtil.getRequest();
        try {
            if(annotation != null)
            {
                if (this.isRepeatSubmit(request))
                {
                    return Result.failure(ResultCode.REPEAT_SUBMIT);
                }
            }
            data = joinPoint.proceed();
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 判断是否存在重复提交的问题
     *
     * @param request 请求实体
     * */
    public boolean isRepeatSubmit(HttpServletRequest request) throws Exception{

        String parameters = JSON.toJSONString(request.getParameterMap());
        Map<String, Object> nowDataMap = new HashMap<String, Object>();

        nowDataMap.put(REPEAT_PARAMS, parameters);
        nowDataMap.put(REPEAT_TIME, System.currentTimeMillis());

        String url = request.getRequestURI();
        String userId = userContext.getUserId();
        String key = url + ":" + userId;

        Map<String,Object> preDataMap = redisTemplate.opsForValue().get(key);

        if(preDataMap != null) {
            if (compareParams(nowDataMap, preDataMap) && compareTime(nowDataMap, preDataMap))
            {
                return true;
            }
        }
        redisTemplate.opsForValue().set(key,nowDataMap,20, TimeUnit.SECONDS);
        return false;
    }

    /**
     * 判断参数是否相同
     */
    private boolean compareParams(Map<String, Object> nowMap, Map<String, Object> preMap)
    {
        String nowParams = (String) nowMap.get(REPEAT_PARAMS);
        String preParams = (String) preMap.get(REPEAT_PARAMS);
        return nowParams.equals(preParams);
    }

    /**
     * 判断两次间隔时间
     */
    private boolean compareTime(Map<String, Object> nowMap, Map<String, Object> preMap)
    {
        long time1 = (Long) nowMap.get(REPEAT_TIME);
        long time2 = (Long) preMap.get(REPEAT_TIME);
        if ((time1 - time2) < (this.intervalTime * 1000))
        {
            return true;
        }
        return false;
    }

    /**
     * 获 取 注 解
     * */
    public Repeat getAnnotation(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        Repeat targetRepeat = targetClass.getAnnotation(Repeat.class);
        if ( targetRepeat != null) {
            return targetRepeat;
        } else {
            Method method = signature.getMethod();
            Repeat repeat = method.getAnnotation(Repeat.class);
            return repeat;
        }
    }
}
