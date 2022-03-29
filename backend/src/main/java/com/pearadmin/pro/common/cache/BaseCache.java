package com.pearadmin.pro.common.cache;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

/**
 * Base Cache
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@EnableScheduling
public abstract class BaseCache<T> {

    /**
     * 容器
     * */
    public Map<String,T> cache = new HashMap<>();

    /**
     * 获取
     * */
    public T get(String key)
    {
        return cache.get(key);
    }

    /**
     * 刷新
     * */
    public void reload()
    {
        cache = load();
    }

    /**
     * 加载
     * */
    public abstract Map<String,T> load();

    /**
     * 刷新
     * */
    @Scheduled(fixedDelay = 30000)
    public void time()
    {
        cache = load();
    }
}
