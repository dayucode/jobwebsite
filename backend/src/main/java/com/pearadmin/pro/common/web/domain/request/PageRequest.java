package com.pearadmin.pro.common.web.domain.request;

import lombok.Data;

/**
 * Describe: 分 页 参 数 封 装
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class PageRequest {

    /**
     * 当前页
     * */
    private Integer page;

    /**
     * 每页数量
     * */
    private Integer limit;

    /**
     * 获取开始的数据行
     * */
    public Integer start(){
        return (this.page-1)*this.limit;
    }

    /**
     * 获取结束的数据行
     * */
    public Integer end(){
        return this.page*this.limit;
    }

}
