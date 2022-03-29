package com.pearadmin.pro.common.quartz.base;

/**
 * Base Quartz
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/05/09
 * */
public interface BaseQuartz {

    /**
     * 任 务 实 现
     * */
    void run(String param) throws Exception;

}
