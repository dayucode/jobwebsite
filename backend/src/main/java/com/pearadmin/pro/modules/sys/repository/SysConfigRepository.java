package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.domain.SysConfig;
import com.pearadmin.pro.modules.sys.param.SysConfigRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysConfigRepository extends BaseMapper<SysConfig> {

    /**
     * 获取配置列表
     *
     * @param request 查询参数
     *
     * @return {@link SysConfig}
     * */
    List<SysConfig> selectConfig(@Param("request") SysConfigRequest request);

}
