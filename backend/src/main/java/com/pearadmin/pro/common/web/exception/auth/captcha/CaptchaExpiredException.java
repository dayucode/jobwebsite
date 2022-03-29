package com.pearadmin.pro.common.web.exception.auth.captcha;

/**
 * 验 证 码 过 期 异 常
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/23
 * */
public class CaptchaExpiredException extends CaptchaException {

    public CaptchaExpiredException(String message){ super(message); }
}
