package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.domain.SysDictData;
import com.pearadmin.pro.modules.sys.param.SysDictDataRequest;

import java.util.List;

public interface SysDictDataService  extends IService<SysDictData> {

    /**
     * 获取字典值列表
     *
     * @param request 查询参数
     * */
    List<SysDictData> list(SysDictDataRequest request);

    /**
     * 获取字典值列表 (分页)
     *
     * @param request 查询参数
     * */
    PageInfo<SysDictData> page(SysDictDataRequest request);
}
