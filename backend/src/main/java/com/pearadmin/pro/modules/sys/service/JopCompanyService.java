package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.entity.JopCompany;
import com.pearadmin.pro.modules.sys.param.JopCompanyRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JopCompanyService extends IService<JopCompany> {
    List<JopCompany> list(JopCompanyRequest request);
    PageInfo<Map<String,Object>> page(@Param("request") JopCompanyRequest request);
}
