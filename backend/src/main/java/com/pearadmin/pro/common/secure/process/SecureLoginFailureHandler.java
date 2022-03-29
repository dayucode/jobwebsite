package com.pearadmin.pro.common.secure.process;

import com.pearadmin.pro.common.aop.enums.Action;
import com.pearadmin.pro.common.context.BaseContext;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.ResultCode;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Describe: Security 登陆失败处理类
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */
@Component
public class SecureLoginFailureHandler implements AuthenticationFailureHandler {

    @Resource
    private BaseContext context;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {

        e.printStackTrace();

        if(e instanceof UsernameNotFoundException){ ServletUtil.writeJson(Result.failure(ResultCode.USER_USERNAME_NOT_FOUND)); return;}
        if(e instanceof LockedException){ ServletUtil.writeJson(Result.failure(ResultCode.USER_LOCKED)); return;}
        if(e instanceof BadCredentialsException){ ServletUtil.writeJson(Result.failure(ResultCode.USER_BAD_CREDENTIALS)); return;}
        if(e instanceof AccountExpiredException){ ServletUtil.writeJson(Result.failure(ResultCode.USER_EXPIRED)); return;}
        if(e instanceof DisabledException){ ServletUtil.writeJson(Result.failure(ResultCode.USER_NOT_ENABLE)); return;}

        context.record("登录","登录失败", Action.AUTH, false, "","");

        ServletUtil.writeJson(Result.failure(ResultCode.LOGIN_FAILURE));
    }
}
