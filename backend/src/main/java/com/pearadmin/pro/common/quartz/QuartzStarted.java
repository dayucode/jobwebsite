package com.pearadmin.pro.common.quartz;

import com.pearadmin.pro.modules.job.domain.SysJob;
import com.pearadmin.pro.modules.job.service.SysJobService;
import org.quartz.CronTrigger;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * 启动检测
 * */
@Component
public class QuartzStarted {

    @Resource
    private QuartzService quartzService;

    @Resource
    private SysJobService sysJobService;

    @PostConstruct
    public void init (){
        List<SysJob> scheduleJobList = sysJobService.list();
        for (SysJob sysJob : scheduleJobList) {
            CronTrigger cronTrigger = quartzService.getCronTrigger(sysJob.getId()) ;
            if (cronTrigger == null){
                quartzService.create (sysJob);
            } else {
                quartzService.update (sysJob);
            }
        }
    }
}
