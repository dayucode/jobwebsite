package com.pearadmin.pro.common.cache.impl;

import lombok.extern.slf4j.Slf4j;
import java.util.stream.Collectors;
import com.pearadmin.pro.common.cache.BaseCache;
import com.pearadmin.pro.modules.sys.domain.SysConfig;
import com.pearadmin.pro.modules.sys.param.SysConfigRequest;
import com.pearadmin.pro.modules.sys.service.SysConfigService;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 全局配置缓存
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/21
 * */
@Slf4j
@Component
public class AllocationCache extends BaseCache<String> {

    @Resource
    private SysConfigService sysConfigService;

    @Override
    public Map<String, String> load()
    {
        log.info("Refresh Cache - 全局配置");
        List<SysConfig> list = sysConfigService.list(new SysConfigRequest());
        return list.stream().collect(Collectors.toMap(SysConfig::getKey, SysConfig::getValue));
    }
}
