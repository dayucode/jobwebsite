package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.entity.JopBlog;
import com.pearadmin.pro.modules.sys.param.JopBlogRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JopBlogService extends IService<JopBlog> {
    PageInfo<Map<String,Object>> page(@Param("request") JopBlogRequest request);
    List<Map<String,Object>> get(@Param("request") JopBlogRequest request);
}
