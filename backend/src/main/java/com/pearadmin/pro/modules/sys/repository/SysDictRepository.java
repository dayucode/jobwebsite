package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.domain.SysDict;
import com.pearadmin.pro.modules.sys.param.SysDictRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDictRepository extends BaseMapper<SysDict> {

    /**
     * 获取字典分类列表
     *
     * @param request 查询参数
     *
     * @return {@link SysDict}
     * */
    List<SysDict> selectDict(@Param("request") SysDictRequest request);
}
