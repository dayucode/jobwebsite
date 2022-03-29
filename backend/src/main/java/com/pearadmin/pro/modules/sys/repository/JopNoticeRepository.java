package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.entity.JopCompany;
import com.pearadmin.pro.modules.sys.entity.JopNotice;
import com.pearadmin.pro.modules.sys.param.JopCompanyRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface JopNoticeRepository extends BaseMapper<JopNotice> {
}