package com.pearadmin.pro.modules.not.domain;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.Alias;
import java.time.LocalDateTime;

/**
 * 用户拉取 - 系统公告
 * <p>
 * author: 就眠仪式
 * date: 2021-05-12
 * */
@Data
@Alias("SysAnnounceUser")
@TableName("sys_announce_user")
public class SysAnnounceUser {

    /**
     * 编号
     * */
    @TableId("id")
    private String id;

    /**
     * 公告编号
     * */
    @TableField("announce_id")
    private String announceId;

    /**
     * 用户编号
     * */
    @TableField("user_id")
    private String userId;

    /**
     * 已读
     * */
    @TableField("is_read")
    private Boolean isRead;

    /**
     * 阅读时间
     * */
    @TableField("read_time")
    private LocalDateTime readTime;

}
