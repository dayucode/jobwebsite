package com.pearadmin.pro.common.secure.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearadmin.pro.common.context.BaseContext;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.ResultCode;
import com.pearadmin.pro.common.constant.TokenConstant;
import org.springframework.security.core.Authentication;
import com.pearadmin.pro.common.secure.uutoken.SecureUserTokenService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * Security 注销成功处理类
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class SecureLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private BaseContext context;

    @Resource
    private SecureUserTokenService customUserDetailsTokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        String token = httpServletRequest.getHeader(TokenConstant.TOKEN_HEADER).replaceFirst(TokenConstant.TOKEN_PREFIX, "");
        customUserDetailsTokenService.destroyToken(token);
        ServletUtil.writeJson(Result.success(ResultCode.LOGOUT_SUCCESS));
    }
}
