package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.domain.request.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 岗位列表 -- 参数实体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021-04-01
 */
@Data
@Alias("SysPostRequest")
public class SysPostRequest extends PageRequest {

    /**
     * 岗位名称
     * */
    private String name;

    /**
     * 岗位编号
     * */
    private String code;
}
