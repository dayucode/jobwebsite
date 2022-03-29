package com.pearadmin.pro.common.secure.services;

import com.pearadmin.pro.modules.sys.domain.SysRole;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 基础 UserInfo 实体, 为安全验证提供支持的公共属性
 * <p>
 * Author: 就 眠 仪 式
 * CreateTime: 2021/10/23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SecureUser extends BaseDomain implements UserDetails, CredentialsContainer {

    /**
     * 编号
     */
    @TableId(value = "id")
    private String id;

    /**
     * 账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 启用
     */
    @TableField(value = "enable")
    private String enable;

    /**
     * 锁定
     */
    @TableField(value = "locked")
    private Boolean locked;

    /**
     * 角色
     * */
    @TableField(exist = false)
    private List<SysRole> roles;

    /**
     * 权限
     */
    @TableField(exist = false)
    private Set<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isEnabled() {
        return this.enable.equals("1");
    }

    // TODO 目前意义不大, 暂不实现
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
