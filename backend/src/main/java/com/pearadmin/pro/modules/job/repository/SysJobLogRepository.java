package com.pearadmin.pro.modules.job.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.job.domain.SysJobLog;
import com.pearadmin.pro.modules.job.param.SysJobLogRequest;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 任务日志模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Mapper
public interface SysJobLogRepository extends BaseMapper<SysJobLog> {

    /**
     * 获取任务日志列表
     *
     * @return {@link SysJobLog}
     * */
    List<SysJobLog> selectJobLog(SysJobLogRequest request);
}
