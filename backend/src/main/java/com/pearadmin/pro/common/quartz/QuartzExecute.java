package com.pearadmin.pro.common.quartz;

import com.pearadmin.pro.common.context.BeanContext;
import com.pearadmin.pro.modules.job.domain.SysJob;
import com.pearadmin.pro.modules.job.domain.SysJobLog;
import com.pearadmin.pro.modules.job.service.SysJobLogService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class QuartzExecute extends QuartzJobBean {

    /**
     * 1.记录日志
     * 2.执行任务
     * */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        SysJob jobBean = (SysJob) context.getMergedJobDataMap().get(SysJob.JOB_PARAM_KEY);

        /// 运行信息
        SysJobLog sysJobLog = new SysJobLog();
        sysJobLog.setJobId(jobBean.getId());
        sysJobLog.setCreateTime(LocalDateTime.now());

        SysJobLogService sysJobLogService = BeanContext.getBean(SysJobLogService.class);

        try {
            /// 执行任务
            long startTime = System.currentTimeMillis();

            Object target = BeanContext.getBean(jobBean.getBeanName());
            Method method = target.getClass().getDeclaredMethod("run", String.class);
            method.invoke(target, jobBean.getParam());

            long endTime = System.currentTimeMillis();

            /// 附加信息
            sysJobLog.setTime(endTime - startTime);
            sysJobLog.setState(true);

        } catch (Exception e) {

            sysJobLog.setError(e.getMessage());
            sysJobLog.setState(false);

            /// 异常信息
            e.printStackTrace();

        } finally {

            /// 记录日志
            sysJobLogService.save(sysJobLog);

        }
    }
}
