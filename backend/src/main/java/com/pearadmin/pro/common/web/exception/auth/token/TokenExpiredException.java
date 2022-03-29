package com.pearadmin.pro.common.web.exception.auth.token;

/**
 * TOKEN 过 期 异 常
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/23
 * */
public class TokenExpiredException extends TokenException{

    public TokenExpiredException(String message){ super(message); }
}
