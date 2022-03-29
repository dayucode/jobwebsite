package com.pearadmin.pro.modules.sys.param;

import lombok.Data;
import com.pearadmin.pro.modules.sys.domain.SysTenant;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import java.util.ArrayList;
import java.util.List;

@Data
public class SysTenantSaveRequest {

    /**
     * 租户
     * */
    private SysTenant tenant;

    /**
     * 账户
     * */
    private SysUser user;

    /**
     * 权限
     * */
    private List<String> powerIds = new ArrayList<>();

}
