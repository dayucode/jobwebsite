package com.pearadmin.pro.modules.sns.cloud.impl;

import lombok.Data;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.sms")
public class CloudSendConfig {

    private String accessKeyId;

    private String accessKeySecret;

    private String domain;

    private String regionId;

    private String signName;

    private String templateCode;

    @Bean
    public IAcsClient initSmsClient() {
        DefaultProfile profile = DefaultProfile.getProfile (regionId, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }
}
