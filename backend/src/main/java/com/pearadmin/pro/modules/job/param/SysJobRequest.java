package com.pearadmin.pro.modules.job.param;

import com.pearadmin.pro.common.web.domain.request.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysJobRequest extends PageRequest {

    /**
     * 任务名称
     * */
    private String name;

    /**
     * 运行目标
     * */
    private String beanName;
}
