package com.pearadmin.pro.common.tools.support.server.server;

import lombok.Data;

/**
 * 磁盘信息
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Data
public class Disk
{
    /**
     * 盘符路径
     */
    private String dirName;

    /**
     * 盘符类型
     */
    private String sysTypeName;

    /**
     * 文件类型
     */
    private String typeName;

    /**
     * 总大小
     */
    private String total;

    /**
     * 剩余大小
     */
    private String free;

    /**
     * 已经使用量
     */
    private String used;

    /**
     * 资源的使用率
     */
    private double usage;

}
