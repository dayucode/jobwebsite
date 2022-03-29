package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.domain.SysTenant;
import com.pearadmin.pro.modules.sys.param.SysTenantRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SysTenantRepository extends BaseMapper<SysTenant> {

    /**
     * 获取租户列表
     *
     * @param request 查询参数
     *
     * @return {@link SysTenant}
     * */
    List<SysTenant> selectTenant(@Param("request") SysTenantRequest request);



}
