package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.entity.JopPosition;
import com.pearadmin.pro.modules.sys.param.JopPositionRequest;
import com.pearadmin.pro.modules.sys.repository.JopPositionRepository;
import com.pearadmin.pro.modules.sys.service.JopPositionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class JopPositionServiceImpl extends ServiceImpl<JopPositionRepository, JopPosition> implements JopPositionService {
    @Resource
    private JopPositionRepository jopPositionRepository;
    @Override
    public List<Map<String,Object>> list(JopPositionRequest request) {
        return jopPositionRepository.selectJopPosition(request);
    }
    @Override
    public PageInfo<Map<String,Object>> page(JopPositionRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(jopPositionRepository.selectJopPosition(request));
    }

    @Override
    public Map<String, Object> position(JopPositionRequest request) {
        return jopPositionRepository.selectJopPositionInfo(request);
    }
}
