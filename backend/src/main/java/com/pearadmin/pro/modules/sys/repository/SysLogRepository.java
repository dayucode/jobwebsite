package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.common.web.interceptor.annotation.DataScope;
import com.pearadmin.pro.common.web.interceptor.annotation.DataScopeRule;
import com.pearadmin.pro.common.web.interceptor.enums.Scope;
import com.pearadmin.pro.modules.sys.domain.SysLog;
import com.pearadmin.pro.modules.sys.param.SysLogRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysLogRepository extends BaseMapper<SysLog> {

    /**
     * 获取日志列表
     *
     * @param request 查询参数
     *
     * @return {@link SysLog}
     * */
    @DataScope(
            rules = {
                    @DataScopeRule(role="admin", scope=Scope.SELF),
                    @DataScopeRule(role="user", scope=Scope.SELF),
            }
    )
    List<SysLog> selectLog(@Param("request") SysLogRequest request);
}
