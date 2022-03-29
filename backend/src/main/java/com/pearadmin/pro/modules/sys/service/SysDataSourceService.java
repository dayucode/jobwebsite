package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.domain.SysDataSource;
import com.pearadmin.pro.modules.sys.param.SysDataSourceRequest;

import java.util.List;

public interface SysDataSourceService extends IService<SysDataSource> {

    /**
     * 获取数据源列表
     *
     * @param request 查询参数
     *
     * @return {@link SysDataSource}
     * */
    List<SysDataSource> list(SysDataSourceRequest request);

    /**
     * 获取数据源列表 (分页)
     *
     * @param request 查询参数
     *
     * @return {@link SysDataSource}
     * */
    PageInfo<SysDataSource> page(SysDataSourceRequest request);

}
