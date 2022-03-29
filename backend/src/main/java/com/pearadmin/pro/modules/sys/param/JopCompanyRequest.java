package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.domain.request.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Alias("JopCompanyRequest")
public class JopCompanyRequest extends PageRequest {
    private String userId;
    private String companyName;
    private String businessNo;
    private String legal;
    private String type;
    private Integer capital;
    private String scale;
    private String detailedPlace;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String phone;
    private String email;
    private String status;
    private LocalDateTime createTime;
}
