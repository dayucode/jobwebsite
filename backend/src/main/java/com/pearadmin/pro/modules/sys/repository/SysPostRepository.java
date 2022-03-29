package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.domain.SysPost;
import com.pearadmin.pro.modules.sys.param.SysPostRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPostRepository extends BaseMapper<SysPost> {

    /**
     * 获取岗位列表
     *
     * @return {@link SysPost}
     * */
    List<SysPost> selectPost(@Param("request") SysPostRequest request);
}
