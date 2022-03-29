package com.pearadmin.pro.common.web.exception.auth.token;

/**
 * TOKEN 验 证 异 常
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/23
 * */
public class TokenValidationException extends TokenException{

    public TokenValidationException(String message){
        super(message);
    }
}
