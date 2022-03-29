package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.domain.SysConfig;
import com.pearadmin.pro.modules.sys.repository.SysConfigRepository;
import com.pearadmin.pro.modules.sys.param.SysConfigRequest;
import com.pearadmin.pro.modules.sys.service.SysConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigRepository, SysConfig> implements SysConfigService {

    @Resource
    private SysConfigRepository sysConfigRepository;

    @Override
    public List<SysConfig> list(SysConfigRequest request) {
        return sysConfigRepository.selectConfig(request);
    }

    @Override
    public PageInfo<SysConfig> page(SysConfigRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(sysConfigRepository.selectConfig(request));
    }
}
