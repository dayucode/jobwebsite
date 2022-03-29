package com.pearadmin.pro.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Alias("jopNotice")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("jop_notice")
public class JopNotice {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "", example = "")
    private String id;
    @ApiModelProperty(value = "", example = "")
    private String avatar;
    @ApiModelProperty(value = "", example = "")
    private String title;
    @ApiModelProperty(value = "", example = "")
    private String context;
    @ApiModelProperty(value = "", example = "")
    private String form;
    @ApiModelProperty(value = "", example = "")
    private LocalDateTime time;
    @ApiModelProperty(value = "", example = "")
    private String type;
    @ApiModelProperty(value = "", example = "")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "", example = "")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "", example = "")
    private String enable;

}
