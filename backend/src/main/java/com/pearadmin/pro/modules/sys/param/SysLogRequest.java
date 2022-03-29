package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.domain.request.PageRequest;
import lombok.Data;

/**
 * 日志列表 -- 参数实体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 */
@Data
public class SysLogRequest extends PageRequest {

    /**
     * 标题
     * */
    private String title;

    /**
     * 类型
     * */
    private Boolean isAuth;

    /**
     * 状态
     * */
    private Boolean state;
}
