package com.pearadmin.pro.modules.job.demo;

import com.pearadmin.pro.common.quartz.base.BaseQuartz;
import org.springframework.stereotype.Component;

@Component("simple")
public class SimpleJob implements BaseQuartz {

    @Override
    public void run(String param) throws Exception {
        System.out.println("执行 Simple 任务");
    }
}
