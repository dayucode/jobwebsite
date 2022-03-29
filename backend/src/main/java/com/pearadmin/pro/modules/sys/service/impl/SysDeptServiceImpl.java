package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import com.pearadmin.pro.modules.sys.repository.SysDeptRepository;
import com.pearadmin.pro.modules.sys.service.SysDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptRepository, SysDept> implements SysDeptService {

    @Resource
    private SysDeptRepository sysDeptRepository;

    @Override
    public List<SysDept> list(SysDept sysDept) {
        return sysDeptRepository.selectDept(sysDept);
    }
}
