package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.context.DataContext;
import com.pearadmin.pro.modules.sys.domain.SysDataSource;
import com.pearadmin.pro.modules.sys.param.SysDataSourceRequest;
import com.pearadmin.pro.modules.sys.repository.SysDataSourceRepository;
import com.pearadmin.pro.modules.sys.service.SysDataSourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public class SysDataSourceServiceImpl extends ServiceImpl<SysDataSourceRepository, SysDataSource>
implements SysDataSourceService {

    @Resource
    private SysDataSourceRepository sysDataSourceRepository;

    @Resource
    private DataContext dataContext;

    @Override
    public List<SysDataSource> list(SysDataSourceRequest request) {
        return sysDataSourceRepository.selectDataSource(request);
    }

    @Override
    public PageInfo<SysDataSource> page(SysDataSourceRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(sysDataSourceRepository.selectDataSource(request));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SysDataSource entity) {
        dataContext.createDataSource(entity.getName(), entity.getUsername(), entity.getPassword(), entity.getUrl(), entity.getDriver());
        sysDataSourceRepository.insert(entity);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(SysDataSource entity) {
        SysDataSource dataSource = sysDataSourceRepository.selectById(entity.getId());
        sysDataSourceRepository.updateById(entity);
        dataContext.updateDataSource(dataSource.getName(), dataSource.getUsername(), dataSource.getPassword(), dataSource.getUrl(), dataSource.getDriver());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        SysDataSource dataSource = sysDataSourceRepository.selectById(id);
        sysDataSourceRepository.deleteById(id);
        dataContext.removeDataSource(dataSource.getName());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.forEach(id -> {
           SysDataSource dataSource = sysDataSourceRepository.selectById(id);
           sysDataSourceRepository.deleteById(id);
           dataContext.removeDataSource(dataSource.getName());
        });
        return true;
    }
}
