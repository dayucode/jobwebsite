package com.pearadmin.pro.common.quartz;

import com.pearadmin.pro.modules.job.domain.SysJob;
import org.quartz.*;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
public class QuartzService {

    @Resource
    private Scheduler scheduler;

    /**
     * 触发器 KEY
     *
     * @param jobId 任务编号
     */
    public TriggerKey getTriggerKey(String jobId){
        return TriggerKey.triggerKey(jobId) ;
    }

    /**
     * 定时器 Key
     *
     * @param jobId 任务编号
     */
    public JobKey getJobKey (String jobId){
        return JobKey.jobKey(jobId) ;
    }

    /**
     * 表达式触发器
     *
     * @param jobId 任务编号
     */
    public CronTrigger getCronTrigger (String jobId){
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId)) ;
        } catch (SchedulerException e){
            return null;
        }
    }

    /**
     * 创建定时任务
     *
     * @param sysJob 任务信息
     * */
    public void create (SysJob sysJob){
        try {
            JobDetail jobDetail = JobBuilder.newJob(QuartzExecute.class).withIdentity(getJobKey(sysJob.getId())).build() ;
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                    .cronSchedule(sysJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing() ;
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(sysJob.getId()))
                    .withSchedule(scheduleBuilder).build();
            jobDetail.getJobDataMap().put(sysJob.JOB_PARAM_KEY,sysJob);
            scheduler.scheduleJob(jobDetail,trigger);
            if(!sysJob.getEnable()){
                pause(sysJob.getId());
            }
        } catch (SchedulerException e){
            throw new RuntimeException("createJob Fail",e) ;
        }
    }

    /**
     * 更新定时任务
     *
     * @param sysJob 任务信息
     * */
    public void update (SysJob sysJob) {
        try {
            TriggerKey triggerKey = getTriggerKey(sysJob.getId());
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(sysJob.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = getCronTrigger(sysJob.getId());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            trigger.getJobDataMap().put(sysJob.JOB_PARAM_KEY, sysJob);
            scheduler.rescheduleJob(triggerKey, trigger);
            if(!sysJob.getEnable()){
                pause(sysJob.getId());
            }
        } catch (SchedulerException e) {
            throw new RuntimeException("updateJob Fail",e);
        }
    }

    /**
     * 停止定时任务
     *
     * @param jobId 任务编号
     * */
    public void pause (String jobId){
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e){
            throw new RuntimeException("pauseJob Fail",e);
        }
    }

    /**
     * 恢复定时任务
     *
     * @param jobId
     * */
    public void resume (String jobId){
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e){
            throw new RuntimeException("resumeJob Fail",e);
        }
    }

    /**
     * 删除定时任务
     *
     * @param jobId 任务编号
     * */
    public void remove (String jobId){
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e){
            throw new RuntimeException("deleteJob Fail",e);
        }
    }

    /**
     * 执行定时任务
     *
     * @param sysJob 任务编号
     * */
    public void run (SysJob sysJob){
        try {
            JobDataMap dataMap = new JobDataMap() ;
            dataMap.put(sysJob.JOB_PARAM_KEY,sysJob);
            scheduler.triggerJob(getJobKey(sysJob.getId()),dataMap);
        } catch (SchedulerException e){
            throw new RuntimeException("run Fail",e) ;
        }
    }

}
