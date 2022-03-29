package com.pearadmin.pro.common.web.domain.response;

/**
 * Describe: Ajax 响应类型
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public enum ResultCode {

    LAY_SUCCESS(0, "成功"),
    SUCCESS(200, "成功"),
    FAILURE(500, "失败"),
    OPERATE_SUCCESS(200, "操作成功"),
    OPERATE_FAILURE(500, "操作失败"),

    LOGIN_SUCCESS(200, "登录成功"),
    LOGIN_FAILURE(500, "登录失败"),
    LOGOUT_SUCCESS(200,"注销成功"),
    NOT_LOGIN(401,"未登录"),
    NOT_PERMISSION(403, "未授权"),

    CAPTCHA_EXPIRED(500,"验证码过期"),
    CAPTCHA_INVALID(500,"验证码无效"),
    CAPTCHA_KEY_MISSION(500,"验证码 KEY 缺失"),
    CAPTCHA_CODE_MISSION(500,"验证码 CODE 缺失"),

    TOKEN_INVALID(501, "Token 无效"),
    TOKEN_EXPIRED(502, "Token 过期"),
    TOKEN_MISSION(503, "Token 缺失"),

    REPEAT_SUBMIT(701,"不允许重复提交，请稍后再试"),

    USER_EXPIRED(604,"账户过期"),
    USER_BAD_CREDENTIALS(603,"密码不正确"),
    USER_USERNAME_NOT_FOUND(602,"用户不存在"),
    USER_LOCKED(601,"用户锁定"),
    USER_NOT_ENABLE(605,"用户未启用");

    /**
     * 标识
     * */
    private int code;

    /**
     * 消息
     * */
    private String message;

    /**
     * 构 造 方 法
     * */
    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    /**
     * 获 取 标 识
     * */
    public int code(){
        return code;
    }

    /**
     * 获 取 消 息
     * */
    public String message(){
        return message;
    }

}
