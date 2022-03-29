package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.entity.JopPosition;
import com.pearadmin.pro.modules.sys.param.JopPositionRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JopPositionService extends IService<JopPosition> {
    List<Map<String,Object>> list(JopPositionRequest request);
    PageInfo<Map<String,Object>> page(@Param("request") JopPositionRequest request);

    Map<String, Object> position(JopPositionRequest request);
}
