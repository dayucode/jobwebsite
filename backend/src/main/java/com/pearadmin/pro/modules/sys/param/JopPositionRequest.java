package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.domain.request.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Alias("JopPositionRequest")
public class JopPositionRequest extends PageRequest {
    private String id;
    private String userId;
    private String title;
    private String introduction;
    private Integer highSalary;
    private Integer lowSalary;
    private String educationRequire;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String categoryCode;
    private LocalDateTime createTime;
    private String enable;
    private String searchWord;
    private String salaryRange;
}
