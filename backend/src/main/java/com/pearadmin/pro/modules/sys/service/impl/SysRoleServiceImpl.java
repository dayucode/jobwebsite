package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.web.interceptor.enums.Scope;
import com.pearadmin.pro.modules.sys.domain.*;
import com.pearadmin.pro.modules.sys.param.SysRoleGiveRequest;
import com.pearadmin.pro.modules.sys.param.SysRoleRequest;
import com.pearadmin.pro.modules.sys.repository.SysDeptRepository;
import com.pearadmin.pro.modules.sys.repository.SysPowerRepository;
import com.pearadmin.pro.modules.sys.repository.SysRoleRepository;
import com.pearadmin.pro.modules.sys.service.SysRoleDeptService;
import com.pearadmin.pro.modules.sys.service.SysRolePowerService;
import com.pearadmin.pro.modules.sys.service.SysRoleService;
import com.pearadmin.pro.modules.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleRepository, SysRole> implements SysRoleService {

    @Resource
    private SysDeptRepository sysDeptRepository;

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysRolePowerService sysRolePowerService;

    @Resource
    private SysRoleDeptService sysRoleDeptService;

    @Resource
    private SysPowerRepository sysPowerRepository;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public List<SysDept> dept(String roleId) {
        return sysDeptRepository.selectDeptByRoleId(roleId);
    }

    @Override
    public List<SysRole> list(SysRoleRequest request) {
        return sysRoleRepository.selectRole(request);
    }

    @Override
    public PageInfo<SysRole> page(SysRoleRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(sysRoleRepository.selectRole(request));
    }

    @Override
    @Transactional
    public Boolean give(SysRoleGiveRequest request) {
        String roleId = request.getRoleId();
        List<String> powerIds = request.getPowerIds();
        List<String> deptIds = request.getDeptIds();
        Scope scope = request.getScope();

        sysRolePowerService.lambdaUpdate().eq(SysRolePower::getRoleId, roleId).remove();
        sysRoleDeptService.lambdaUpdate().eq(SysRoleDept::getRoleId, roleId).remove();
        this.lambdaUpdate().eq(SysRole::getRoleId, roleId).set(SysRole::getScope, scope).update();

        List<SysRolePower> rolePowers = new ArrayList<>();
        powerIds.forEach(powerId -> {
            SysRolePower rolePower = new SysRolePower();
            rolePower.setRoleId(roleId);
            rolePower.setPowerId(powerId);
            rolePowers.add(rolePower);
        });

        List<SysRoleDept> roleDepts = new ArrayList<>();
        deptIds.forEach(deptId -> {
            SysRoleDept roleDept = new SysRoleDept();
            roleDept.setRoleId(roleId);
            roleDept.setDeptId(deptId);
            roleDepts.add(roleDept);
        });

        sysRolePowerService.saveBatch(rolePowers);
        sysRoleDeptService.saveBatch(roleDepts);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        sysRoleRepository.deleteById(id);
        sysRolePowerService.lambdaUpdate().eq(SysRolePower::getRoleId,id).remove();
        sysUserRoleService.lambdaUpdate().eq(SysUserRole::getRoleId,id).remove();
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.forEach(this::removeById);
        return true;
    }
    /**
     * Describe: 修改用户数据
     * Param: SysUser
     * Return: 操作结果
     * */
    @Override
    public boolean update(SysRole sysRole) {
        Integer result = sysRoleRepository.updateById(sysRole);
        return result > 0;
    }

    @Override
    public List<SysPower> getRolePower(String roleId) {
        List<SysPower> allPower = sysPowerRepository.selectList(new SysPower());
        QueryWrapper<SysRolePower> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        List<SysRolePower> myPower =  sysRolePowerService.list(queryWrapper);
        allPower.forEach(sysPower->{
            myPower.forEach(sysRolePower->{
                if(sysRolePower.getPowerId().equals(sysPower.getPowerId())){
                    sysPower.setCheckArr("1");
                }
            });
        });
        return allPower;
    }

    @Override
    public List<SysDept> getRoleDept(String roleId) {
        List<SysDept> allDept = sysDeptRepository.selectDept(new SysDept());
        QueryWrapper<SysRoleDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        List<SysRoleDept> myDept =  sysRoleDeptService.list(queryWrapper);
        allDept.forEach(sysDept->{
            myDept.forEach(sysRoleDept->{
                if(sysRoleDept.getDeptId().equals(sysDept.getDeptId())){
                    sysDept.setCheckArr("1");
                }
            });
        });
        return allDept;
    }
}
