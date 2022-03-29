package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.domain.request.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * 用户列表 -- 参数实体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 */
@Data
@Alias("SysUserRequest")
public class SysUserRequest extends PageRequest {

    /** 账户 */
    private String username;

    /** 昵称 */
    private String nickname;

    /** 部分编号 */
    private List<String> deptIds;

}
