package com.pearadmin.pro.common.web.exception.base;

/**
 * 业 务 异 常
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class BusinessException extends RuntimeException {

    public BusinessException(String message){
        super(message);
    }
}