package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.web.interceptor.annotation.DataScope;
import com.pearadmin.pro.common.web.interceptor.enums.Scope;
import com.pearadmin.pro.modules.sys.domain.SysLog;
import com.pearadmin.pro.modules.sys.repository.SysLogRepository;
import com.pearadmin.pro.modules.sys.param.SysLogRequest;
import com.pearadmin.pro.modules.sys.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogRepository, SysLog> implements SysLogService {

    @Resource
    private SysLogRepository sysLogRepository;

    @Override
    public List<SysLog> list(SysLogRequest request) {
        return sysLogRepository.selectLog(request);
    }

    @Override
    @DataScope(scope = Scope.SELF)
    public PageInfo<SysLog> page(SysLogRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(sysLogRepository.selectLog(request));
    }
}
