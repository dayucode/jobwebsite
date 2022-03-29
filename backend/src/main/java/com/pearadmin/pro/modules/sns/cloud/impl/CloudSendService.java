package com.pearadmin.pro.modules.sns.cloud.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.pearadmin.pro.modules.sns.cloud.SendService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudSendService implements SendService {

    @Resource
    private CloudSendConfig cloudSendConfig;

    @Resource
    private IAcsClient iAcsClient;

    @Override
    public boolean sendSample(String phone, String message) {
        CommonRequest request = new CommonRequest();
        request.setSysDomain(cloudSendConfig.getDomain());
        request.setSysMethod(MethodType.POST);
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("RegionId", cloudSendConfig.getRegionId());
        request.putQueryParameter("SignName", cloudSendConfig.getSignName());
        request.putQueryParameter("TemplateCode", cloudSendConfig.getTemplateCode());
        Map<String, Object> params = new HashMap<>();
        params.put("code", message);
        request.putQueryParameter("TemplateParam", JSON.toJSONString(params));
        try {
            CommonResponse response = iAcsClient.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sendTemplate(String phone, String templateCode, Map<String, String> params) {
        CommonRequest request = new CommonRequest();
        request.setSysDomain(cloudSendConfig.getDomain());
        request.setSysMethod(MethodType.POST);
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("RegionId", cloudSendConfig.getRegionId());
        request.putQueryParameter("SignName", cloudSendConfig.getSignName());
        request.putQueryParameter("TemplateParam", JSON.toJSONString(params));
        try {
            CommonResponse response = iAcsClient.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
