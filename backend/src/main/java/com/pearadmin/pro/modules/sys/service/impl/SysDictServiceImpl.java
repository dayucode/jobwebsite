package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.modules.sys.domain.SysDict;
import com.pearadmin.pro.modules.sys.domain.SysDictData;
import com.pearadmin.pro.modules.sys.repository.SysDictRepository;
import com.pearadmin.pro.modules.sys.param.SysDictRequest;
import com.pearadmin.pro.modules.sys.service.SysDictDataService;
import com.pearadmin.pro.modules.sys.service.SysDictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictRepository, SysDict> implements SysDictService {

    @Resource
    private SysDictRepository sysDictRepository;

    @Resource
    private SysDictDataService sysDictDataService;

    @Override
    public List<SysDict> list(SysDictRequest request) {
        return sysDictRepository.selectDict(request);
    }

    @Override
    public PageInfo<SysDict> page(SysDictRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(sysDictRepository.selectDict(request));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        SysDict sysDict = sysDictRepository.selectById(id);
        sysDictRepository.deleteById(id);
        sysDictDataService.lambdaUpdate().eq(SysDictData::getCode,sysDict.getCode()).remove();
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.forEach(this::removeById);
        return true;
    }
}
