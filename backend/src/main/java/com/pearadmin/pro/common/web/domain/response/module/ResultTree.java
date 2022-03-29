package com.pearadmin.pro.common.web.domain.response.module;

import lombok.Data;

/**
 * Describe: 前端 tree 结果封装数据
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class ResultTree {

    /**
     * 状态信息
     * */
    private Status status = new Status();

    /**
     * 返回数据
     * */
    private Object data;

    /**
     * 所需内部类
     * */
    @Data
    public class Status{

        private Integer code = 200;

        private String message = "默认";
    }

}
