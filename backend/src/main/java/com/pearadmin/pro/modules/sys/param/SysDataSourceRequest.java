package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.domain.request.PageRequest;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysDataSourceRequest extends PageRequest {

    /**
     * 多库名称
     * */
    private String name;

}
