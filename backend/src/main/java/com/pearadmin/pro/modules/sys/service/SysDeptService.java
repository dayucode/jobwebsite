package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.modules.sys.domain.SysDept;

import java.util.List;

public interface SysDeptService extends IService<SysDept> {

    /**
     * 查询部门列表
     * */
    List<SysDept> list(SysDept sysDept);

}
