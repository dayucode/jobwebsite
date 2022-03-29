package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.entity.JopResumePosition;
import com.pearadmin.pro.modules.sys.param.JopResumePositionRequest;
import com.pearadmin.pro.modules.sys.repository.JopResumePositionRepository;
import com.pearadmin.pro.modules.sys.service.JopResumePositionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Map;

@Service
public class JopResumePositionServiceImpl extends ServiceImpl<JopResumePositionRepository, JopResumePosition> implements JopResumePositionService {

    @Resource
    private JopResumePositionRepository jopResumePositionRepository;
    @Override
    public PageInfo<Map<String,Object>> page(JopResumePositionRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(jopResumePositionRepository.selectJopResumePosition(request));
    }
}
