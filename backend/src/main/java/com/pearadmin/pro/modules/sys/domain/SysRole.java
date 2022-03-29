package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

@Data
@Alias("SysRole")
@Accessors(chain = true)
@ApiModel("角色实体")
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseDomain {

    /**
     * 编号
     */
    @TableId("role_id")
    @ApiModelProperty("角色编号")
    private String roleId;

    /**
     * 名称
     */
    @TableField("role_name")
    @ApiModelProperty("角色名称")
    private String roleName;

    /**
     * 标识
     */
    @TableField("role_code")
    @ApiModelProperty("角色标识")
    private String roleCode;

    /**
     * 启用
     */
    @TableField("enable")
    @ApiModelProperty("是否开启")
    private String enable;

    /**
     * 排序
     */
    @TableField("details")
    @ApiModelProperty("详情")
    private String details;

    /**
     * 排序
     */
    @TableField("sort")
    @ApiModelProperty("排序")
    private Integer sort;
    /**
     * 数据权限范围
     */
    @TableField("scope")
    @ApiModelProperty("数据权限范围")
    private String scope;
    /**
     * 提供给前端组件
     * */
    @TableField(exist = false)
    private boolean checked = false;

}
