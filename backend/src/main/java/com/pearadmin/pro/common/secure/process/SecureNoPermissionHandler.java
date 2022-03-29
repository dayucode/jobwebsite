package com.pearadmin.pro.common.secure.process;

import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.common.web.domain.response.Result;
import com.pearadmin.pro.common.web.domain.response.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Security 用户暂无权限处理类
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/23
 * */
@Component
public class SecureNoPermissionHandler implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        ServletUtil.writeJson(Result.failure(ResultCode.NOT_PERMISSION));
    }
}
