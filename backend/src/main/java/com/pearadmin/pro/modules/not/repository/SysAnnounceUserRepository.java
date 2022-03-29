package com.pearadmin.pro.modules.not.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.not.domain.SysAnnounceUser;
import com.pearadmin.pro.modules.not.param.SysAnnounceUserRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysAnnounceUserRepository extends BaseMapper<SysAnnounceUser> {

    /**
     * 获取公告列表
     *
     * @return {@link SysAnnounceUser}
     * */
    List<SysAnnounceUser> selectAnnounceUser(@Param("request")SysAnnounceUserRequest request);
}
