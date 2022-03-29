package com.pearadmin.pro.modules.not.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.not.domain.SysAnnounceUser;
import com.pearadmin.pro.modules.not.param.SysAnnounceUserRequest;
import com.pearadmin.pro.modules.not.repository.SysAnnounceUserRepository;
import com.pearadmin.pro.modules.not.service.SysAnnounceUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysAnnounceUserServiceImpl extends ServiceImpl<SysAnnounceUserRepository, SysAnnounceUser>
implements SysAnnounceUserService {

    @Resource
    private SysAnnounceUserRepository sysAnnounceUserRepository;

    @Override
    public List<SysAnnounceUser> list(SysAnnounceUserRequest request) {
        return sysAnnounceUserRepository.selectAnnounceUser(request);
    }

    @Override
    public PageInfo<SysAnnounceUser> page(SysAnnounceUserRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return new PageInfo<>(sysAnnounceUserRepository.selectAnnounceUser(request));
    }
}
