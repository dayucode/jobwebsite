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
@Alias("JopResumePosition")
@Accessors(chain = true)
@ApiModel(value = "", description = "")
@EqualsAndHashCode(callSuper = false)
@TableName("jop_resume_position")
public class JopResumePosition {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "", example = "")
    private String id;
    @ApiModelProperty(value = "", example = "")
    private String resumeId;
    @ApiModelProperty(value = "", example = "")
    private String positionId;
    @ApiModelProperty(value = "", example = "")
    private String type;
    @ApiModelProperty(value = "", example = "")
    private String msg;
    @ApiModelProperty(value = "", example = "")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "", example = "")
    private LocalDateTime replyTime;
    @ApiModelProperty(value = "", example = "")
    private String acceptStatus;
}
