package com.pearadmin.pro.common.secure.services;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.repository.SysUserRepository;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Security 用户服务实现
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/23
 * */
@Slf4j
@Component
public class SecureUserService implements UserDetailsService {

    @Resource
    private SysUserRepository sysUserRepository;

    @Resource
    private SysUserService sysUserService;

    /**
     * 加载用户信息
     * */
    @Override
    public SecureUser loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserRepository.selectByUsername(username);
        if(sysUser==null) {
            throw new UsernameNotFoundException("USERNAME NOT SUPPORT");
        }
        sysUser.setAuthorities(loadAuthorities(sysUser.getId()));
        sysUser.setRoles(loadRoles(sysUser.getId()));
        return sysUser;
    }

    public Set<? extends GrantedAuthority> loadAuthorities(String userId){
        Set<SimpleGrantedAuthority> authoritySet = new HashSet<>();
        List<SysPower> powers = sysUserService.power(userId);
        for (SysPower power : powers) {
            if (!StringUtils.isEmpty(power.getPowerCode())){
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(power.getPowerCode());
                authoritySet.add(authority);
            }
        }
        return authoritySet;
    }

    public List<SysRole> loadRoles(String userId) {
        return sysUserService.role(userId);
    }
}
