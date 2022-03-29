package com.pearadmin.pro.modules.not.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;

/**
 * 系统公告
 * <p>
 * author: 就眠仪式
 * date: 2021-05-12
 * */
@Data
@Alias("SysAnnounce")
@TableName("sys_announce")
public class SysAnnounce extends BaseDomain {

    /**
     * 公告编号
     * */
    @TableId("id")
    private String id;

    /**
     * 公告标题
     * */
    @TableField("title")
    private String title;

    /**
     * 公告内容
     * */
    @TableField("content")
    private String content;

    /**
     * 状态
     *
     * 0.未发布
     * 1.已发布
     *
     * */
    @TableField("state")
    private Boolean state = true;

    /**
     * 创建人
     * */
    @TableField(exist = false)
    private String createName;

}
