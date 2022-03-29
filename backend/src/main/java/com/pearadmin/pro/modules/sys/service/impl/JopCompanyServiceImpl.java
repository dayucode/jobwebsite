package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.entity.JopCompany;
import com.pearadmin.pro.modules.sys.param.JopCompanyRequest;
import com.pearadmin.pro.modules.sys.repository.JopCompanyRepository;
import com.pearadmin.pro.modules.sys.service.JopCompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class JopCompanyServiceImpl extends ServiceImpl<JopCompanyRepository, JopCompany> implements JopCompanyService {

    @Resource
    private JopCompanyRepository jopCompanyRepository;

    @Override
    public List<JopCompany> list(JopCompanyRequest request) {
        return jopCompanyRepository.selectJopCompany(request);
    }

    @Override
    public PageInfo<Map<String, Object>> page(JopCompanyRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return  PageInfo.of(jopCompanyRepository.selectCompanyMap(request));
    }
}
