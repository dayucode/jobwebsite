package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import com.pearadmin.pro.modules.sys.repository.SysPowerRepository;
import com.pearadmin.pro.modules.sys.service.SysPowerService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysPowerServiceImpl extends ServiceImpl<SysPowerRepository, SysPower> implements SysPowerService {

    @Resource
    private SysPowerRepository sysPowerRepository;

    @Resource
    private UserContext userContext;


    /**
     * 查询权限
     * param sysPower
     * return 执行结果
     */
    @Override
    public List<SysPower> list(SysPower sysPower) {
        return sysPowerRepository.selectList(sysPower);
    }


    /**
     * Describe: 修改权限数据
     * Param: SysPower
     * Return: 执行结果
     * */
    @Override
    public boolean update(SysPower sysPower) {
        int result = sysPowerRepository.updateById(sysPower);
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }
}
