package com.pearadmin.pro.common.web.exception.auth.captcha;

import org.springframework.security.core.AuthenticationException;

/**
 * 验 证 码 异 常 基 类
 *
 * Author: 就眠仪式
 * CreateTime: 2021/04/23
 * */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String message){
        super(message);
    }

}
