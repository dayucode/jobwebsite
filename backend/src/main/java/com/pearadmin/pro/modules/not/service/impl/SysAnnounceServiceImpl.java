package com.pearadmin.pro.modules.not.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.not.domain.SysAnnounce;
import com.pearadmin.pro.modules.not.param.SysAnnounceRequest;
import com.pearadmin.pro.modules.not.repository.SysAnnounceRepository;
import com.pearadmin.pro.modules.not.service.SysAnnounceService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysAnnounceServiceImpl extends ServiceImpl<SysAnnounceRepository, SysAnnounce> implements SysAnnounceService
{
    @Resource
    private SysAnnounceRepository sysAnnounceRepository;

    @Override
    public List<SysAnnounce> list(SysAnnounceRequest request) {
        return sysAnnounceRepository.selectAnnounce(request);
    }

    @Override
    public PageInfo<SysAnnounce> page(SysAnnounceRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(sysAnnounceRepository.selectAnnounce(request));
    }
}
