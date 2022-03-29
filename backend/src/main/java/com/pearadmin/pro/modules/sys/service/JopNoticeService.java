package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.entity.JopNotice;
import com.pearadmin.pro.modules.sys.param.JopNoticeRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JopNoticeService extends IService<JopNotice> {
    PageInfo<Map<String,Object>> page(@Param("request") JopNoticeRequest request);
    List<Map<String,Object>> get(@Param("request") JopNoticeRequest request);
}
