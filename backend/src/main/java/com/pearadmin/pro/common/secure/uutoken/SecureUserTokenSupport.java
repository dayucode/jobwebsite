package com.pearadmin.pro.common.secure.uutoken;

import com.pearadmin.pro.common.constant.SecurityConstant;
import com.pearadmin.pro.common.constant.TokenConstant;
import com.pearadmin.pro.common.context.BeanContext;
import com.pearadmin.pro.common.secure.services.SecureUser;
import com.pearadmin.pro.common.tools.core.PatternUtil;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.ResultCode;
import com.pearadmin.pro.common.web.exception.auth.token.TokenExpiredException;
import com.pearadmin.pro.common.web.exception.auth.token.TokenValidationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Token Filter 主要增加 Token 的验证
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
public class SecureUserTokenSupport extends OncePerRequestFilter {

    @Resource
    private BeanContext beanContext;

    private SecureUserTokenService customUserDetailsTokenService;

    public SecureUserTokenSupport(){
        // Secure Details
        this.customUserDetailsTokenService = beanContext.getBean("secureUserTokenService", SecureUserTokenService.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(TokenConstant.TOKEN_HEADER);
        String tokenHeaderKey = request.getHeader(TokenConstant.TOKEN_HEADER_KEY);
        // token param verify empty
        if (tokenHeader == null) { ServletUtil.writeJson(Result.failure(ResultCode.TOKEN_MISSION)); return;}
        // token verify
        SecureUser secureUser;
        try {
            SecureUserToken userToken = customUserDetailsTokenService.verifyToken(tokenHeaderKey,tokenHeader.replaceFirst(TokenConstant.TOKEN_PREFIX, ""));
            secureUser = userToken.getSecureUser();
        }catch (TokenValidationException e){
            ServletUtil.writeJson(Result.failure(ResultCode.TOKEN_INVALID)); return;
        }catch (TokenExpiredException e){
            ServletUtil.writeJson(Result.failure(ResultCode.TOKEN_EXPIRED)); return;
        }
        // return UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(secureUser, secureUser.getId(), secureUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }


    // TODO 修正匹配策略
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        List<String> pattern = Arrays.asList(SecurityConstant.HTTP_ACT_MATCHERS.split(","));
        return PatternUtil.matches(pattern, ServletUtil.getRequestURI());
    }
}
