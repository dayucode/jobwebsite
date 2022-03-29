package com.pearadmin.pro.modules.job.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * 任务日志模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */
@Data
@Alias("SysJobLog")
public class SysJobLog {

    /**
     * 编号
     * */
    private String id;

    /**
     * 任务编号
     * */
    private String jobId;

    /**
     * 任务名称
     * */
    @TableField(exist = false)
    private String jobName;

    /**
     * 运行目标
     * */
    @TableField(exist = false)
    private String beanName;

    /**
     * 状态
     * */
    private Boolean state;

    /**
     * 错误信息
     * */
    private String error;

    /**
     * 运行时长
     * */
    private long time;

    /**
     * 执行时间
     * */
    private LocalDateTime createTime;

}
