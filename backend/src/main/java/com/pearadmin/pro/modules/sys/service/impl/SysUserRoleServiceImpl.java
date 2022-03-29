package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.modules.sys.domain.SysUserRole;
import com.pearadmin.pro.modules.sys.repository.SysUserRoleRepository;
import com.pearadmin.pro.modules.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleRepository, SysUserRole> implements SysUserRoleService {
}
