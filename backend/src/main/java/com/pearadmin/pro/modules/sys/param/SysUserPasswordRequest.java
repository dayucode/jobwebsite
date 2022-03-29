package com.pearadmin.pro.modules.sys.param;

import lombok.Data;

@Data
public class SysUserPasswordRequest {
    /**
     * 密码 - 新
     * */
    private String newPassword;

    /**
     * 密码 - 旧
     * */
    private String oldPassword;
    /**
     * 确认密码
     * */
    private String confirmPassword;
}
