package com.pearadmin.pro.common.tools.secure;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Describe: Security 工 具 类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class SecurityUtil {

    /**
     * 获取当前登录用户的信息
     * return Authentication 权鉴对象
     * */
    public static Authentication currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication;
        }
        return null;
    }

    /**
     * 验证当前用户是否登录
     * @return boolean 是否登录
     * */
    public static boolean isAuthentication(){
        // if security session eq s-id is not null to index
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return true;
        }else{
            return false;
        }
    }

}
