package com.pearadmin.pro.modules.not.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.Alias;
import java.time.LocalDateTime;

/**
 * 私人消息
 * <p>
 * author: 就眠仪式
 * date: 2021-05-12
 * */
@Data
@Alias("SysInbox")
@TableName("sys_inbox")
public class SysInbox extends BaseDomain {

    /**
     * 编号
     * */
    @TableId("id")
    private String id;

    /**
     * 对话编号
     * */
    @TableField("dialogue_id")
    private String dialogueId;

    /**
     * 接受者
     * */
    @TableField("recipient_id")
    private String recipientId;

    /**
     * 消息内容
     * */
    @TableField("content")
    private String content;

    /**
     * 标记
     * */
    @TableField("is_read")
    private Boolean isRead;

    /**
     * 发送时间
     * */
    @TableField("send_time")
    private LocalDateTime sendTime;

    /**
     * 阅读时间
     * */
    @TableField("read_time")
    private LocalDateTime readTime;

    /**
     * 创建人
     * */
    @TableField(exist = false)
    private String createName;

    /**
     * 接收人
     * */
    @TableField(exist = false)
    private String recipientName;

}
