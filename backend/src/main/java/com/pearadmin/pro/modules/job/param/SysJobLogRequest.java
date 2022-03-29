package com.pearadmin.pro.modules.job.param;

import com.pearadmin.pro.common.web.domain.request.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysJobLogRequest extends PageRequest {

    /**
     * 任务名称
     * */
    private String jobName;

    /**
     * 执行状态
     * */
    private Boolean state;

}
