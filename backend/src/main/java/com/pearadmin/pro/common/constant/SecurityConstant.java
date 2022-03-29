package com.pearadmin.pro.common.constant;

/**
 * Security 静态配置
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
public class SecurityConstant {

    /**
     * 不需要认证的接口资源
     */
    public static final String HTTP_ACT_MATCHERS="/webjars/springfox-swagger-ui/fonts/**" +
            ",/swagger-resources,/swagger-resources/configuration/**,/webjars**," +
            "/swagger-ui.html,/webjars/springfox-swagger-ui/**,/v2/api-docs," +
            "/api/login,/api/captcha/create," +
            "/api/sys/user/register," +
            "/api/sys/resume/view/**," +
            "/api/sys/blog/listIndex," +
            "/api/sys/blog/IndexOne," +
            "/api/sys/position/noAuth/**";;

    /**
     * 不需要认证的静态资源
     * */
    public static final String WEB_ACT_MATCHERS= "/favicon.ico";

    /**
     * 默认的登录接口
     * */
    public static final String LOGIN_URL = "/api/login";
    /**
     * 默认的登录接口
     * */
    public static final String Register_URL = "/api/sys/user/register";

    /**
     * 登录接口方式
     * */
    public static final String LOGIN_METHOD = "POST";

    /**
     * 默认的注销接口
     * */
    public static final String LOGOUT_URL = "/api/logout";

    /**
     * 是否开启验证码
     * */
    public static final Boolean IS_CAPTCHA_VERIFICATION = true;

    /**
     * Captcha 过期时间
     * */
    public static final long CAPTCHA_EXPIRATION = 1000 * 60 * 5;

    /**
     * Token 过期时间
     * */
    public static final long TOKEN_EXPIRATION = 1000 * 60 * 30;

}
