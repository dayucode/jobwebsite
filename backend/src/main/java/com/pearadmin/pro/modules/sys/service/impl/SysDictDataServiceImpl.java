package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.domain.SysDictData;
import com.pearadmin.pro.modules.sys.repository.SysDictDataRepository;
import com.pearadmin.pro.modules.sys.param.SysDictDataRequest;
import com.pearadmin.pro.modules.sys.service.SysDictDataService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataRepository, SysDictData> implements SysDictDataService {

    @Resource
    private SysDictDataRepository sysDictDataRepository;

    @Override
    public List<SysDictData> list(SysDictDataRequest request) {
        return sysDictDataRepository.selectDictData(request);
    }

    @Override
    public PageInfo<SysDictData> page(SysDictDataRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(sysDictDataRepository.selectDictData(request));
    }
}
