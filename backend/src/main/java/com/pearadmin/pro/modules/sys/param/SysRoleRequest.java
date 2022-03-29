package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.domain.request.PageRequest;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * 角色列表 -- 参数实体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 */
@Data
@Alias("SysRoleRequest")
@Accessors(chain = true)
public class SysRoleRequest extends PageRequest {

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 权限标识
     */
    private String roleCode;
}
