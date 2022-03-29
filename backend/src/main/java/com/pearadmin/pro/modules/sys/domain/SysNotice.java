package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;

@EqualsAndHashCode(callSuper = true)
@Data
@Alias("SysNotice")
@TableName("sys_notice")
public class SysNotice extends BaseDomain {

    /**
     * 编号
     * */
    @TableId("id")
    private String id;

    /**
     * 标题
     * */
    @TableField("title")
    private String title;

    /**
     * 内容
     * */
    @TableField("content")
    private String content;

    /**
     * 类型
     *
     * 0.公告 1.通知 2.私信
     * */
    @TableField("type")
    private int type;

    /**
     * 目标
     *
     * 0.公告 - 目标：全部
     * 1.通知 - 目标：部门
     * 2.私信 - 目标: 个人
     * */
    @TableField("subject")
    private String subject;

    /**
     * 状态
     *
     * true 发布
     * false 未发布
     * */
    private Boolean state;

}
