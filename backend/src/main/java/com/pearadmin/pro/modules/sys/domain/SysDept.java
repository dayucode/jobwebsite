package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 部门模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Alias("SysDept")
@TableName("sys_dept")
@EqualsAndHashCode(callSuper = true)
public class SysDept extends BaseDomain {

    /**
     * 部门 ID
     * */
    @TableId("dept_id")
    private String deptId;
    /**
     * 父级组织编号
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 部门名称
     * */
    @TableField("dept_name")
    private String deptName;

    /**
     * 显示顺序
     * */
    @TableField("sort")
    private Integer sort;

    /**
     * 是否启用
     * */
    @TableField("enable")
    private String enable;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    /**
     * 计算列 提供给前端组件
     * */
    @TableField(exist = false)
    private String checkArr = "0";
}
