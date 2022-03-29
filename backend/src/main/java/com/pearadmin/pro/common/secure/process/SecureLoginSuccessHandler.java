package com.pearadmin.pro.common.secure.process;

import com.pearadmin.pro.common.aop.enums.Action;
import com.pearadmin.pro.common.context.BaseContext;
import com.pearadmin.pro.common.secure.services.SecureUser;
import com.pearadmin.pro.common.secure.uutoken.SecureUserToken;
import com.pearadmin.pro.common.secure.uutoken.SecureUserTokenService;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.ResultCode;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Describe: Security 登陆成功处理类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class SecureLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private BaseContext context;

    @Resource
    private SecureUserTokenService customUserDetailsTokenService;

    @Resource
    private SysUserService sysUserRoleService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
            SecureUser secureUser = (SecureUser) authentication.getPrincipal();

            SecureUserToken userToken = customUserDetailsTokenService.createToken(secureUser);
            String tokenKey = customUserDetailsTokenService.saveToken(userToken);
            String tokenValue = userToken.getToken();
            String loginTo = "home.html";
            List<SysRole> roles = sysUserRoleService.role(userToken.getSecureUser().getId());
            for (SysRole role:roles){
                if (role.getRoleCode().equals("admin")) {
                    loginTo = "index.html";
                    break;
                }
            }
            // 登 录 日 志
            context.record("登录","登录成功", Action.AUTH, true, "","");
            ServletUtil.writeJson(Result.success(ResultCode.LOGIN_SUCCESS,tokenKey,tokenValue,loginTo));
    }
}
