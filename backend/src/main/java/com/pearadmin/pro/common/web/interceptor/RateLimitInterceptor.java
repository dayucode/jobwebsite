package com.pearadmin.pro.common.web.interceptor;

import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.common.web.interceptor.annotation.RateLimit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    @Resource
    private UserContext userContext;

    @Resource
    private RedisTemplate<String, Long> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RateLimit currentLimit = handlerMethod.getMethodAnnotation(RateLimit.class);
            // 存在注解
            if (currentLimit != null) {
                // 限流条件
                int number = currentLimit.number();
                long time = currentLimit.time();

                System.out.println("限流时间:" + time);

                // 开启限流
                if (time > 0 && number > 0) {
                    // 创建主键
                    String key = request.getContextPath() + ":" + request.getServletPath() + ":" + ServletUtil.getIpAddress();
                    Long numberRedis = redisTemplate.opsForValue().get(key);
                    Long timeRedis = redisTemplate.getExpire(key);
                    // 初次访问
                    if (null == numberRedis) {
                        redisTemplate.opsForValue().set(key, 1L, time, TimeUnit.SECONDS);
                        return true;
                    }
                    // 流量溢出
                    if (numberRedis >= number) {
                        throw new RuntimeException("请求频繁，请稍后重试！");
                    }
                    // 更新缓存
                    redisTemplate.opsForValue().set(key, numberRedis + 1, timeRedis, TimeUnit.SECONDS);
                }
            }
        }
        return true;
    }
}
