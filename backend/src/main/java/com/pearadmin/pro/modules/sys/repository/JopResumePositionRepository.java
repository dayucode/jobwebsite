package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.entity.JopResumePosition;
import com.pearadmin.pro.modules.sys.param.JopResumePositionRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface JopResumePositionRepository extends BaseMapper<JopResumePosition> {

    List<Map<String, Object>> selectJopResumePosition(@Param("request") JopResumePositionRequest request);
}