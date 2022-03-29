package com.pearadmin.pro.modules.sns.service.impl;

import com.pearadmin.pro.common.constant.SystemConstant;
import com.pearadmin.pro.modules.sns.cloud.SendConfig;
import com.pearadmin.pro.modules.sns.cloud.impl.CloudSendService;
import com.pearadmin.pro.modules.sns.service.SysSmsService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class SysSmsServiceImpl implements SysSmsService {

    @Resource
    private SendConfig sendConfig;

    @Resource
    private CloudSendService cloudSendService;

    @Override
    public boolean send(String phone, String message) {
        if(sendConfig.getLocation().equals(SystemConstant.ALIYUN)) {
           return cloudSendService.sendSample(phone, message);
        }
        return false;
    }
}
