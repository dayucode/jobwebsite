package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.entity.JopPosition;
import com.pearadmin.pro.modules.sys.param.JopPositionRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface JopPositionRepository extends BaseMapper<JopPosition> {
    /**
     * 查询
     * @param request
     * @return
     */
    List<Map<String,Object>> selectJopPosition(@Param("request")JopPositionRequest request);
    Map<String, Object> selectJopPositionInfo(@Param("request")JopPositionRequest request);
}