package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.tools.string.StringUtil;
import com.pearadmin.pro.modules.sys.entity.JopBlog;
import com.pearadmin.pro.modules.sys.param.JopBlogRequest;
import com.pearadmin.pro.modules.sys.repository.JopBlogRepository;
import com.pearadmin.pro.modules.sys.service.JopBlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class JopBlogServiceImpl extends ServiceImpl<JopBlogRepository, JopBlog> implements JopBlogService {

    @Resource
    private JopBlogRepository jopBlogRepository;

    @Override
    public PageInfo<Map<String, Object>> page(JopBlogRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        QueryWrapper<JopBlog> queryWrapper = new QueryWrapper<>();
        if (!StringUtil.isEmpty(request.getTitle())){
            queryWrapper.eq("title",request.getTitle());
        }
        if (!StringUtil.isEmpty(request.getType())){
            queryWrapper.eq("type",request.getType());
        }
        queryWrapper.orderByDesc("create_time");
        return  PageInfo.of(jopBlogRepository.selectMaps(queryWrapper));
    }
    @Override
    public List<Map<String,Object>> get(JopBlogRequest request) {
        QueryWrapper<JopBlog> queryWrapper = new QueryWrapper<>();
        if (!StringUtil.isEmpty(request.getTitle())){
            queryWrapper.eq("title",request.getTitle());
        }
        if (!StringUtil.isEmpty(request.getType())){
            queryWrapper.eq("type",request.getType());
        }
        queryWrapper.orderByDesc("create_time");
        return  jopBlogRepository.selectMaps(queryWrapper);
    }
}
