package com.pearadmin.pro.modules.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.quartz.QuartzService;
import com.pearadmin.pro.modules.job.domain.SysJob;
import com.pearadmin.pro.modules.job.repository.SysJobRepository;
import com.pearadmin.pro.modules.job.param.SysJobRequest;
import com.pearadmin.pro.modules.job.service.SysJobService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 任务服务实现
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/15
 * */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobRepository, SysJob> implements SysJobService {

    @Resource
    private SysJobRepository sysJobRepository;

    @Resource
    private QuartzService quartzService;

    @Override
    public List<SysJob> list(SysJobRequest request) {
        return sysJobRepository.selectJob(request);
    }

    @Override
    public PageInfo<SysJob> page(SysJobRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(sysJobRepository.selectJob(request));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SysJob entity) {
        sysJobRepository.insert(entity);
        quartzService.create(entity);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(SysJob entity) {
        quartzService.update(entity);
        sysJobRepository.updateById(entity);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        quartzService.remove((String) id);
        sysJobRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.forEach(id -> {
            removeById(id);
        });
        return true;
    }

    @Override
    public Boolean run(String id) {
        quartzService.run(sysJobRepository.selectById(id));
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean resume(String id) {
        quartzService.resume(id);
        SysJob sysJob = sysJobRepository.selectById(id);
        sysJob.setEnable(true);
        sysJobRepository.updateById(sysJob);
        return true;
    }

    @Override
    public Boolean pause(String id) {
        quartzService.pause(id);
        SysJob sysJob = sysJobRepository.selectById(id);
        sysJob.setEnable(false);
        sysJobRepository.updateById(sysJob);
        return true;
    }
}
