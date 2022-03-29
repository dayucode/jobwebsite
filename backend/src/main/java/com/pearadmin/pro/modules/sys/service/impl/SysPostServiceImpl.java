package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.domain.SysPost;
import com.pearadmin.pro.modules.sys.param.SysPostRequest;
import com.pearadmin.pro.modules.sys.repository.SysPostRepository;
import com.pearadmin.pro.modules.sys.service.SysPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostRepository, SysPost> implements SysPostService {

    @Resource
    private SysPostRepository sysPostRepository;

    @Override
    public List<SysPost> list(SysPostRequest request) {
        return sysPostRepository.selectPost(request);
    }

    @Override
    public PageInfo<SysPost> page(SysPostRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(sysPostRepository.selectPost(request));
    }
}
