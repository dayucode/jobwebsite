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
@Alias("JopResume")
@Accessors(chain = true)
@ApiModel(value = "", description = "")
@EqualsAndHashCode(callSuper = false)
@TableName("jop_resume")
public class JopResume {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "", example = "")
    private String id;
    @ApiModelProperty(value = "", example = "")
    private String userId;
    @ApiModelProperty(value = "", example = "")
    private String birth;
    @ApiModelProperty(value = "", example = "")
    private String addr;
    @ApiModelProperty(value = "", example = "")
    private String apply;
    @ApiModelProperty(value = "", example = "")
    private Integer highSalary;
    @ApiModelProperty(value = "", example = "")
    private Integer lowSalary;
    @ApiModelProperty(value = "", example = "")
    private String expectWorkPlace;
    @ApiModelProperty(value = "", example = "")
    private String graduationTime;
    @ApiModelProperty(value = "", example = "")
    private String education;
    @ApiModelProperty(value = "", example = "")
    private String school;
    @ApiModelProperty(value = "", example = "")
    private String startWorkTime;
    @ApiModelProperty(value = "", example = "")
    private String educationalExperience;
    @ApiModelProperty(value = "", example = "")
    private String trainingExperience;
    @ApiModelProperty(value = "", example = "")
    private String workExperience;
    @ApiModelProperty(value = "", example = "")
    private String skill;
    @ApiModelProperty(value = "", example = "")
    private String name;
    @ApiModelProperty(value = "", example = "")
    private String portrait;
    @ApiModelProperty(value = "", example = "")
    private String phone;
    @ApiModelProperty(value = "", example = "")
    private String sex;
    @ApiModelProperty(value = "", example = "")
    private String origin;
    @ApiModelProperty(value = "", example = "")
    private String profession;
    @ApiModelProperty(value = "", example = "")
    private String email;
    private LocalDateTime createTime;
    @ApiModelProperty(value = "", example = "")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "", example = "")
    private LocalDateTime createTemplateTime;
}
