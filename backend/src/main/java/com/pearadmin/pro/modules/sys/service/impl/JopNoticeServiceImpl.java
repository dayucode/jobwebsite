package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.tools.string.StringUtil;
import com.pearadmin.pro.modules.sys.entity.JopNotice;
import com.pearadmin.pro.modules.sys.param.JopNoticeRequest;
import com.pearadmin.pro.modules.sys.repository.JopNoticeRepository;
import com.pearadmin.pro.modules.sys.service.JopNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class JopNoticeServiceImpl extends ServiceImpl<JopNoticeRepository, JopNotice> implements JopNoticeService {

    @Resource
    private JopNoticeRepository jopNoticeRepository;

    @Override
    public PageInfo<Map<String, Object>> page(JopNoticeRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        QueryWrapper<JopNotice> queryWrapper = new QueryWrapper<>();
        if (!StringUtil.isEmpty(request.getTitle())){
            queryWrapper.eq("title",request.getTitle());
        }
        if (!StringUtil.isEmpty(request.getType())){
            queryWrapper.eq("type",request.getType());
        }
        queryWrapper.orderByDesc("create_time");
        return  PageInfo.of(jopNoticeRepository.selectMaps(queryWrapper));
    }
    @Override
    public List<Map<String,Object>> get(JopNoticeRequest request) {
        QueryWrapper<JopNotice> queryWrapper = new QueryWrapper<>();
        if (!StringUtil.isEmpty(request.getTitle())){
            queryWrapper.eq("title",request.getTitle());
        }
        if (!StringUtil.isEmpty(request.getType())){
            queryWrapper.eq("type",request.getType());
        }
        queryWrapper.orderByDesc("create_time");
        return  jopNoticeRepository.selectMaps(queryWrapper);
    }
}
