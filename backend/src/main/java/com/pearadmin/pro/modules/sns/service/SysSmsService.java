package com.pearadmin.pro.modules.sns.service;

public interface SysSmsService {

    /**
     * 发送短信
     *
     * @param phone 联系方式
     * @param message 短信内容
     *
     * @return {@link Boolean}
     * */
    boolean send(String phone, String message);

}
