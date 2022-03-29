package com.pearadmin.pro.modules.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.job.domain.SysJobLog;
import com.pearadmin.pro.modules.job.repository.SysJobLogRepository;
import com.pearadmin.pro.modules.job.param.SysJobLogRequest;
import com.pearadmin.pro.modules.job.service.SysJobLogService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 任务日志服务实现
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/15
 * */
@Service
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogRepository, SysJobLog> implements SysJobLogService {

    @Resource
    private SysJobLogRepository sysJobLogRepository;

    @Override
    public List<SysJobLog> list(SysJobLogRequest request) {
        return sysJobLogRepository.selectJobLog(request);
    }

    @Override
    public PageInfo<SysJobLog> page(SysJobLogRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(sysJobLogRepository.selectJobLog(request));
    }
}
