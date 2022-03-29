package com.pearadmin.pro.common.web.exception.auth.token;

import org.springframework.security.core.AuthenticationException;

/**
 * TOKEN 异 常 类
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/23
 * */
public class TokenException extends AuthenticationException {

    public TokenException(String message){
        super(message);
    }
}