package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.entity.JopResumePosition;
import com.pearadmin.pro.modules.sys.param.JopResumePositionRequest;

import java.util.Map;

public interface JopResumePositionService extends IService<JopResumePosition> {
    PageInfo<Map<String,Object>> page(JopResumePositionRequest request);

}
