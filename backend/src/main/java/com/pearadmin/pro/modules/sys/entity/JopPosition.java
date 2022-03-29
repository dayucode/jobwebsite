package com.pearadmin.pro.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Alias("jopPosition")
@Accessors(chain = true)
@ApiModel(value = "", description = "")
@EqualsAndHashCode(callSuper = false)
@TableName("jop_position")
public class JopPosition {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "", example = "")
    private String id;
    @ApiModelProperty(value = "", example = "")
    private String userId;
    @ApiModelProperty(value = "", example = "")
    private String companyId;
    @ApiModelProperty(value = "", example = "")
    private String title;
    @ApiModelProperty(value = "", example = "")
    private String introduction;
    @ApiModelProperty(value = "", example = "")
    private Integer highSalary;
    @ApiModelProperty(value = "", example = "")
    private Integer lowSalary;
    @ApiModelProperty(value = "", example = "")
    private String treatment;
    @ApiModelProperty(value = "", example = "")
    private String requirement;
    @ApiModelProperty(value = "", example = "")
    private String educationRequire;
    @ApiModelProperty(value = "", example = "")
    private Integer workHours;
    @ApiModelProperty(value = "", example = "")
    private Integer workYears;
    @ApiModelProperty(value = "", example = "")
    private String provinceCode;
    @ApiModelProperty(value = "", example = "")
    private String cityCode;
    @ApiModelProperty(value = "", example = "")
    private String detailedPlace;
    @ApiModelProperty(value = "", example = "")
    private String countyCode;
    @ApiModelProperty(value = "", example = "")
    private String enable;
    @ApiModelProperty(value = "", example = "")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "", example = "")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "", example = "")
    private String categoryCode;
    @ApiModelProperty(value = "", example = "")
    private String searWord;
    @ApiModelProperty(value = "", example = "")
    private String salaryRange;
}
