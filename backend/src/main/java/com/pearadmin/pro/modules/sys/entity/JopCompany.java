package com.pearadmin.pro.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Alias("jopCompany")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("jop_company")
public class JopCompany {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "", example = "")
    private String id;
    @ApiModelProperty(value = "", example = "")
    private String userId;
    @ApiModelProperty(value = "", example = "")
    private String companyName;
    @ApiModelProperty(value = "", example = "")
    private String businessNo;
    @ApiModelProperty(value = "", example = "")
    private String legal;
    @ApiModelProperty(value = "", example = "")
    private String type;
    @ApiModelProperty(value = "", example = "")
    private Integer capital;
    @ApiModelProperty(value = "", example = "")
    private String scale;
    @ApiModelProperty(value = "", example = "")
    private String detailedPlace;
    @ApiModelProperty(value = "", example = "")
    private String provinceCode;
    @ApiModelProperty(value = "", example = "")
    private String cityCode;
    @ApiModelProperty(value = "", example = "")
    private String countyCode;
    @ApiModelProperty(value = "", example = "")
    private String phone;
    @ApiModelProperty(value = "", example = "")
    private String email;
    @ApiModelProperty(value = "", example = "")
    private String status;
    @ApiModelProperty(value = "", example = "")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "", example = "")
    private LocalDateTime updateTime;

}
