package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.secure.captcha.SecureCaptchaService;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.modules.sys.domain.*;
import com.pearadmin.pro.modules.sys.param.SysRoleRequest;
import com.pearadmin.pro.modules.sys.param.SysUserRequest;
import com.pearadmin.pro.modules.sys.repository.*;
import com.pearadmin.pro.modules.sys.service.SysUserRoleService;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import com.pearadmin.pro.modules.sys.util.SnowFlakeUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserRepository, SysUser> implements SysUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysUserRepository sysUserRepository;

    @Resource
    private SysDeptRepository sysDeptRepository;

    @Resource
    private SysPowerRepository sysPowerRepository;

    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;
    @Resource
    private SecureCaptchaService secureCaptchaService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserRoleService userRoleService;

    @Override
    public List<SysRole> role(String userId) {
        return sysRoleRepository.selectRoleByUserId(userId);
    }

    @Override
    public List<SysUser> list(SysUserRequest request) {
        return sysUserRepository.selectUser(request);
    }

    @Override
    public PageInfo<SysUser> page(SysUserRequest request) {
        PageHelper.startPage(request.getPage(),request.getLimit());
        return PageInfo.of(sysUserRepository.selectUser(request));
    }

    @Override
    public List<SysPower> power(String userId) {
        return sysPowerRepository.selectPowerByUserId(userId);
    }

    @Override
    public List<SysMenu> menu(String userId) {
        return toTree(sysPowerRepository.selectMenu(userId),"0");
    }

    @Override
    @Transactional
    public Boolean give(String userId, List<String> roleIds) {
        sysUserRoleService.lambdaUpdate().eq(SysUserRole::getUserId, userId).remove();
        List<SysUserRole> userRoles = new ArrayList<>();
        roleIds.forEach(roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        });
        return sysUserRoleService.saveBatch(userRoles);
    }

    public List<SysMenu> toTree(List<SysMenu> sysMenus, String parent) {
        List<SysMenu> list = new ArrayList<>();
        for (SysMenu menu : sysMenus) {
            if (parent.equals(menu.getParentId())) {
                menu.setChildren(toTree(sysMenus, menu.getId()));
                list.add(menu);
            }
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        sysUserRepository.deleteById(id);
        sysUserRoleService.lambdaUpdate().eq(SysUserRole::getUserId,id).remove();
        return true;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.forEach(this::removeById);
        return true;
    }

    @Override
    public List<SysDept> dept(String userId) {
        return sysDeptRepository.selectDeptByUserId(userId);
    }

    @Override
    public Boolean exist(String username) {
        long count = this.lambdaQuery().eq(SysUser::getUsername, username).count();
        if(count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean editPassword(String userId, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setPassword(passwordEncoder.encode(password));
        sysUserRepository.updateById(sysUser);
        return true;
    }

    @Override
    public Boolean resetPassword(String userId) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setPassword(passwordEncoder.encode("123456"));
        sysUserRepository.updateById(sysUser);
        return true;
    }

    @Override
    public Boolean contrastPassword(String userId, String password) {
        SysUser sysUser = this.getById(userId);
        return passwordEncoder.matches(password, sysUser.getPassword());
    }

    /**
     * Describe: 修改用户状态
     * Param: SysPower
     * Return: 执行结果
     * */
    @Override
    public boolean update(SysUser sysUser) {
        int result = sysUserRepository.updateById(sysUser);
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<SysRole> getUserRole(String userId) {
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.eq("enable","1");
        List<SysRole> allRole = sysRoleRepository.selectList(sysRoleQueryWrapper);
        QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
        sysUserRoleQueryWrapper.eq("user_id",userId);
        List<SysUserRole> myRole = sysUserRoleRepository.selectList(sysUserRoleQueryWrapper);
        allRole.forEach(sysRole->{
            myRole.forEach(sysUserRole->{
                if(sysRole.getRoleId().equals(sysUserRole.getRoleId()))sysRole.setChecked(true);
            });
        });
        return allRole;
    }

    @Transactional
    @Override
    public Result register(SysUser sysUser) {
        if(sysUserService.exist(sysUser.getUsername())) {
            return Result.failure("账号已存在");
        }
        String nextStrId = SnowFlakeUtil.getFlowIdInstance().nextStrId();
        sysUser.setId(nextStrId);
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUser.setNickname("用户"+ new Random().nextInt(4));
        sysUser.setLocked(false);
        sysUser.setEnable("1");
        sysUserService.save(sysUser);
        //添加普通用户角色
        List<SysRole> sysRoles = sysRoleRepository.selectRole(new SysRoleRequest().setRoleCode("user"));
        sysUserRoleService.save(new SysUserRole().setUserId(nextStrId).setRoleId(sysRoles.get(0).getRoleId()));
        return Result.auto(true);
    }
}
