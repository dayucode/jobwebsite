package com.pearadmin.pro.modules.not.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.not.domain.SysInbox;
import com.pearadmin.pro.modules.not.param.SysInboxRequest;
import com.pearadmin.pro.modules.not.repository.SysInboxRepository;
import com.pearadmin.pro.modules.not.service.SysInboxService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysInboxServiceImpl extends ServiceImpl<SysInboxRepository, SysInbox> implements SysInboxService {

    @Resource
    private SysInboxRepository sysInboxRepository;

    @Override
    public List<SysInbox> list(SysInboxRequest request) {
        return sysInboxRepository.selectInbox(request);
    }

    @Override
    public PageInfo<SysInbox> page(SysInboxRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(sysInboxRepository.selectInbox(request));
    }
}
