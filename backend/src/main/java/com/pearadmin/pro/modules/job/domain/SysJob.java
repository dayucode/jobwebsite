package com.pearadmin.pro.modules.job.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;

/**
 * 任务模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */
@Data
@Alias("SysJob")
@EqualsAndHashCode(callSuper = true)
public class SysJob extends BaseDomain {

    /**
     * 任务调度 参数 KEY
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    /**
     * 任务编号
     * */
    private String id;

    /**
     * 任务名称
     * */
    private String name;

    /**
     * 运行类
     * */
    private String beanName;

    /**
     * 参数
     * */
    private String param;

    /**
     * 表达式
     * */
    private String cronExpression;

    /**
     * 状态
     * */
    private Boolean enable;

}
