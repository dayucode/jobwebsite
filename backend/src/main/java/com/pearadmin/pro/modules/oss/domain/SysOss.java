package com.pearadmin.pro.modules.oss.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;

/**
 * 文件模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2020/10/23
 * */
@EqualsAndHashCode(callSuper = true)
@Data
@Alias("SysOss")
@TableName("sys_oss")
public class SysOss extends BaseDomain {

    /**
     * 编号
     */
    @TableId
    private String id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 文件存储位置
     *
     * 1：阿里云
     */
    @TableField("location")
    private String location;

    /**
     * 桶
     * */
    @TableField("bucket")
    private String bucket;

    /**
     * 后缀
     */
    @TableField("suffix")
    private String suffix;

    /**
     * 文件大小
     */
    @TableField("size")
    private Long size;

    /**
     * 路径
     */
    @TableField("path")
    private String path;

}
