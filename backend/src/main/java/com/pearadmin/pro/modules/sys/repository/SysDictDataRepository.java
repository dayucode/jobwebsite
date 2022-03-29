package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.domain.SysDictData;
import com.pearadmin.pro.modules.sys.param.SysDictDataRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysDictDataRepository extends BaseMapper<SysDictData> {

    /**
     * 获取字典值列表
     *
     * @param request 查询参数
     *
     * @return {@link SysDictData}
     * */
    List<SysDictData> selectDictData(@Param("request") SysDictDataRequest request);
}
