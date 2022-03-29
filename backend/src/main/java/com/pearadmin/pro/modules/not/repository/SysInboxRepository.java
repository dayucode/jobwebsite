package com.pearadmin.pro.modules.not.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.not.domain.SysInbox;
import com.pearadmin.pro.modules.not.param.SysInboxRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysInboxRepository extends BaseMapper<SysInbox> {

    /**
     * 获取私信列表
     *
     * @return {@link SysInbox}
     * */
    List<SysInbox> selectInbox(@Param("request") SysInboxRequest request);

}
