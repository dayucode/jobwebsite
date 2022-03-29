package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * 权限模型
 * <p>
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Alias("SysPower")
@Accessors(chain = true)
@TableName("sys_power")
@EqualsAndHashCode(callSuper = true)
public class SysPower extends BaseDomain {
    /**
     * 权限编号
     */
    @TableId("power_id")
    private String powerId;
    /**
     * 权限名称
     */
    @TableField("power_name")
    private String powerName;
    /**
     * 权限类型
     */
    @TableField("power_type")
    private String powerType;
    /**
     * 权限路径
     */
    @TableField("power_url")
    private String powerUrl;
    /**
     * 打开方式
     */
    @TableField("open_type")
    private String openType;
    /**
     * 父类编号
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 图标
     */
    @TableField("icon")
    private String icon;
    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 权限标识
     */
    @TableField("power_code")
    private String powerCode;
    /**
     * 是否开启
     */
    @TableField("enable")
    private String enable;
    /**
     * 计算列 提供给前端组件
     * */
    @TableField(exist = false)
    private String checkArr = "0";


}
