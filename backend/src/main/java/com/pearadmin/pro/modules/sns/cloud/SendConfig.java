package com.pearadmin.pro.modules.sns.cloud;

import com.pearadmin.pro.common.constant.SystemConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "sms")
public class SendConfig {

    /**
     * Aliyun 阿里云
     *
     * Qiniu 七牛
     *
     * Tencent 腾讯
     * */
    private String location = SystemConstant.ALIYUN;

}
