package com.pearadmin.pro.modules.not.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.not.domain.SysAnnounce;
import com.pearadmin.pro.modules.not.param.SysAnnounceRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysAnnounceRepository extends BaseMapper<SysAnnounce> {

    /**
     * 获取公告列表
     *
     * @return {@link SysAnnounce}
     * */
    List<SysAnnounce> selectAnnounce(@Param("request")SysAnnounceRequest request);
}
