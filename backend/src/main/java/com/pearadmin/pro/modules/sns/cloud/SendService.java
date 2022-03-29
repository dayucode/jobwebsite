package com.pearadmin.pro.modules.sns.cloud;

import java.util.Map;

public interface SendService {

    /**
     * 发 送 简 单 短 信 ( 预 定 默 认 模 板 内 容 仅 为 code 参数)
     *
     * @param phone 联系方式
     * @param message 短信内容
     * */
    boolean sendSample(String phone, String message);

    /**
     * 发 送 模 板 短 信
     *
     * @param phone 联系方式
     * @param templateCode 模板标识
     * @param params 模板参数
     * */
    boolean sendTemplate(String phone, String templateCode, Map<String,String> params);

}